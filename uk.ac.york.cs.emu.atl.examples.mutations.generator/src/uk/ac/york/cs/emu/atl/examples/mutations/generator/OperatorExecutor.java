package uk.ac.york.cs.emu.atl.examples.mutations.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.eclipse.epsilon.emu.EmuModule;
import org.eclipse.epsilon.emu.execute.EmuPatternMatcher;
import org.eclipse.epsilon.emu.mutation.matrix.OMatrix;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.mutant.IMutant;
import org.eclipse.epsilon.emc.mutant.emf.EmfMutant;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;

import uk.ac.york.cs.emu.atl.examples.mutations.generator.metamodels.EcoreModel;

import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import java.net.URISyntaxException;

public class OperatorExecutor implements Runnable {

	private String trans_module;
	private String trans_model;

	public OperatorExecutor(Map<String, String> config) {
		trans_module = config.get("TRANS_MODULE");
		trans_model = config.get("TRANS_MODEL");

	}

	@Override
	public void run() {

		String metamodel = EcoreModel.class.getResource("ATL.ecore").getPath();
		String metamodel_name = "ATL";
		File mutations_dir = new File("generatedMutations" + File.separatorChar + trans_module);
		mutations_dir.mkdirs();

		EmuModule module = null;
		IMutant emfModel = null;

		File emu_programs_dir = new File("operatorDefinitions" + File.separatorChar);
		final File emu_programs[] = emu_programs_dir.listFiles();

		OMatrix operators_matrix = new OMatrix(mutations_dir.getPath());

		for (File entry : emu_programs) {
			try {
				if (!entry.isDirectory() && entry.getName().endsWith(".emu")) {

					module = new EmuModule();
					module.setMutants_dir(mutations_dir);
					module.setOperatorsMatrix(operators_matrix);
					module.parse(entry.getAbsoluteFile());
					if (module.getParseProblems().size() >= 1) {
						System.err.println("\t- - - - - - - - - - - - - -");
						System.err.println("\t|---->Parsing Problems in emu program: " + entry);
						System.err.println(module.getParseProblems().toString());
						System.err.println("\t- - - - - - - - - - - - - -");
						return;
					}
					emfModel = createEmfModel(metamodel_name, trans_model, metamodel, true, false);
					module.getContext().getModelRepository().addModel(emfModel);
					module.setRepeatWhileMatches(false);
					module.execute();
					module.getContext().getModelRepository().dispose();
				}
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		} // end of executing operators

		try {
			print_summary(operators_matrix,
					"generatedMutations_summary" + File.separatorChar + trans_module + File.separatorChar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private IMutant createEmfModel(String name, String model, String metamodel, boolean readOnLoad,
			boolean storeOnDisposal) throws EolModelLoadingException, URISyntaxException {
		IMutant emfModel = new EmfMutant();
		StringProperties properties = new StringProperties();
		properties.put(EmfMutant.PROPERTY_NAME, name);
		properties.put(EmfMutant.PROPERTY_FILE_BASED_METAMODEL_URI, new URI(metamodel).toString());
		properties.put(EmfMutant.PROPERTY_MODEL_URI, new URI(model).toString());
		properties.put(EmfMutant.PROPERTY_READONLOAD, readOnLoad + "");
		properties.put(EmfMutant.PROPERTY_STOREONDISPOSAL, storeOnDisposal + "");
		emfModel.load(properties, (IRelativePathResolver) null);
		return emfModel;
	}

	private void print_summary(OMatrix matrix, String location) throws IOException {
		if (matrix == null)
			return;
		int valid_mutants;
		int invalid_mutants;
		Iterator<Map.Entry<String, List<String>>> it = matrix.getContent().entrySet().iterator();
		StringBuilder entry = new StringBuilder();

		entry.append("Mutation Operator");
		entry.append(',');
		entry.append("Valid");
		entry.append(',');
		entry.append("Invalid");
		entry.append('\n');
		int totol_valid = 0;
		int totol_invalid = 0;
		while (it.hasNext()) {
			Map.Entry<String, List<String>> pair = it.next();
			List<String> values = pair.getValue();
			valid_mutants = 0;
			invalid_mutants = 0;
			for (int i = 0; i < values.size(); i++) {
				if (values.get(i).equals(EmuPatternMatcher.INVALID_MUTATION))
					invalid_mutants++;
				else
					valid_mutants++;
			}
			totol_valid += valid_mutants;
			totol_invalid += invalid_mutants;

			entry.append(pair.getKey().toString());
			entry.append(',');
			entry.append(String.format("%d", valid_mutants));
			entry.append(',');
			entry.append(String.format("%d", invalid_mutants));
			entry.append('\n');
		}

		entry.append("Total Mutants");
		entry.append(',');
		entry.append(totol_valid);
		entry.append(',');
		entry.append(totol_invalid);

		File folder = new File(location);
		folder.mkdirs();
		try (FileWriter file = new FileWriter(location + trans_module + "_summary.csv")) {
			file.write(entry.toString());
			file.flush();
			file.close();
		}
	}
}
