package uk.ac.york.cs.emu.atl.examples.execution.mutations.launcher;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import uk.ac.york.cs.emu.atl.examples.execution.launcher.AtlLauncher;

public class ATLMutantExecutor implements Callable<String> {

	public static final String KILLED = "killed";
	public static final String NOT_KILLED = "not killed";

	private String trans_module;
	private String inMM_name;
	private String inMM_File;
	private String outMM_name;
	private String outMM_File;
	private File inM_File;
	private File asm_File;
	private File tempOutput;

	public ATLMutantExecutor(String trans_module, String inMMName, String inMMPath, String outMMName, String outMMPath,
			File inMFile, File asmFile, File temp_folder) {
		this.trans_module = trans_module;
		inMM_name = inMMName;
		inMM_File = inMMPath;
		outMM_name = outMMName;
		outMM_File = outMMPath;
		inM_File = inMFile;
		asm_File = asmFile;
		tempOutput = temp_folder;
	}

	@Override
	public String call() {
		// killed = false
		// Not killed = true

		String expectedOutMDir = "expectedModels" + File.separatorChar;

		// register metamodels of in/out models for mutant
		// (transformation) execution
		EMFModelFactory factory = new EMFModelFactory();
		IInjector injector = new EMFInjector();
		IReferenceModel inMetamodel = factory.newReferenceModel();
		IReferenceModel outMetamodel = factory.newReferenceModel();
		try {
			injector.inject(inMetamodel, inMM_File);
			injector.inject(outMetamodel, outMM_File);
		} catch (Exception e) {
			System.err.println("Exception reading/loading metamodels");
			e.printStackTrace();
			return KILLED;
		}

		String input_model_name = inM_File.getName();
		String expected_model_path = input_model_name.substring(0, input_model_name.length() - 4);
		expected_model_path = expectedOutMDir + trans_module + File.separatorChar + expected_model_path + "_result2"
				+ outMM_name + ".xmi";
		String actual_model_path = tempOutput.getPath() + File.separatorChar + "mutant_exection_output.xmi";
		AtlLauncher atl_launcher = new AtlLauncher();
		try {
			atl_launcher.run(factory, inMetamodel, outMetamodel, injector, inMM_name, inM_File.getPath(), outMM_name,
					actual_model_path, asm_File.getPath());
		} catch (ATLCoreException | RuntimeException | IOException | Error e) {
			// The compiled file of this mutant is loaded
			// but not successfully executed
			// e.printStackTrace();

			return KILLED;
		} catch (Exception e) {
			e.printStackTrace();
			return KILLED;
		}

		Resource lhs_resource = null;
		Resource rhs_resource = null;
		// Compare the expected and actual output models
		// load actual/expected output resources
		lhs_resource = atl_launcher.getOutResource();
		if (lhs_resource == null) {
			// Unable to load actual output. This
			// implies no actual output is produced by
			// transformation
			return KILLED;
		}
		// get expected output model
		rhs_resource = atl_launcher.getCurrentResourceSet().getResource(URI.createFileURI(expected_model_path), true);
		if (rhs_resource == null) {
			System.err.println("Unable to load expected model [" + inM_File.getPath() + "] of transformation module ["
					+ trans_module + "]");
		}

		EMFCompare comparator = EMFCompare.builder().build();
		IComparisonScope scope = new DefaultComparisonScope(rhs_resource, lhs_resource, null);
		Comparison result = comparator.compare(scope);
		if (result != null)
			if (result.getDifferences().size() >= 1)
				return KILLED;
		return NOT_KILLED;
	}
}
