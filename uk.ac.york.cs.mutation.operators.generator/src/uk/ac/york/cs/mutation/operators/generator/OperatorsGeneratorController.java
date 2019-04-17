package uk.ac.york.cs.mutation.operators.generator;

import java.io.File;
import java.net.URI;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.emf.EmfModel;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.etl.EtlModule;

public class OperatorsGeneratorController {

	public static void main(String args[]) {
		String sourceMMPath = OperatorsGeneratorController.class.getResource("resources/Ecore.ecore").getPath();
		String targetMMPath = OperatorsGeneratorController.class.getResource("resources/MutationOperatorMM.ecore").getPath();
		String etl_script = OperatorsGeneratorController.class.getResource("resources/etl_script.etl").getPath();

		String atlModelPath = "./metamodel/ATL.ecore";
		String targetMPath;

		EtlModule module;

		String split[] = atlModelPath.split("/");

		File outFolder = new File("./output");
		outFolder.mkdir();
		targetMPath = outFolder.toURI().toString() + "/" + split[split.length - 1] + "_mutation_operators.xmi";

		try {
			IModel atl_mm = createEmfModel("Ecore", atlModelPath, sourceMMPath, true, false);
			IModel targetModel = createEmfModel("MutationOperatorMM", targetMPath, targetMMPath, false, true);

			module = new EtlModule();
			module.getContext().getModelRepository().addModel(atl_mm);
			module.getContext().getModelRepository().addModel(targetModel);

			module.parse(new File(etl_script));
			if (module.getParseProblems().size() >= 1) {
				System.out.println("Parsing Problems: " + module.getParseProblems().toString());
				return;
			}
			module.execute();
			module.getContext().getModelRepository().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static EmfModel createEmfModel(String name, String modelPath, String metamodelPath, boolean readOnLoad, boolean disposal) throws Exception {
		EmfModel model = new EmfModel();
		StringProperties properties = new StringProperties();
		properties.put(EmfModel.PROPERTY_FILE_BASED_METAMODEL_URI, new URI(metamodelPath).toString());
		properties.put(EmfModel.PROPERTY_MODEL_URI, new URI(modelPath).toString());
		properties.put(EmfModel.PROPERTY_READONLOAD, readOnLoad);
		properties.put(EmfModel.PROPERTY_STOREONDISPOSAL, disposal);
		model.load(properties);
		model.setName(name);
		return model;
	}
}
