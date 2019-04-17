package uk.ac.york.cs.emu.atl.examples.execution.transformations.launcher;

import java.io.File;
import java.util.Map;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import uk.ac.york.cs.emu.atl.examples.execution.launcher.ATLCompiler;
import uk.ac.york.cs.emu.atl.examples.execution.launcher.AtlLauncher;

public class ATLOriginalLauncher implements Runnable {

    private String trans_module;
    private String inMM_name;
    private String inMM_File;
    private String outMM_name;
    private String outMM_File;
    private String trans_code;

    public ATLOriginalLauncher(Map<String, String> config) {
	trans_module = config.get("TRANS_MODULE");
	inMM_name = config.get("IN_MM_NAME");
	inMM_File = config.get("IN_MM_FILE");
	outMM_name = config.get("OUT_MM_NAME");
	outMM_File = config.get("OUT_MM_FILE");
	trans_code = config.get("TRANS_CODE");
    }

    @Override
    public void run() {

	String inModelsFolder = "inModels/";
	String outModelsFolder = "expectedModels/";
	File temp = new File(trans_module + "_temp_execution");
	temp.mkdir();
	File inputFolder = new File(inModelsFolder + File.separatorChar + trans_module);

	if (inputFolder.exists() && inputFolder.isDirectory()) {

	    EMFModelFactory factory = new EMFModelFactory();
	    IInjector injector = new EMFInjector();
	    IReferenceModel inMetamodel = factory.newReferenceModel();
	    IReferenceModel outMetamodel = factory.newReferenceModel();

	    try {
		// register metamodels of mutant (transformation) execution
		injector.inject(inMetamodel, inMM_File);
		injector.inject(outMetamodel, outMM_File);
	    } catch (ATLCoreException e) {
		e.printStackTrace();
		return;
	    }

	    ATLCompiler compiler = new ATLCompiler();
	    // Does the atl file compile
	    if (compiler.compileAtlCode(new File(trans_code), temp.getAbsolutePath())) {
		
		// obtain the compiled asm file
		String atlFileName = trans_code.substring(trans_code.lastIndexOf(File.separatorChar), trans_code.length());
		atlFileName = atlFileName.substring(0, atlFileName.length() - 4);
		File asm = new File(temp.getAbsolutePath() + atlFileName + ".asm");
		asm.deleteOnExit();

		for (File entry : inputFolder.listFiles()) {
		    if (entry.getName().endsWith(".xmi")) {
			String input_file = entry.getName();
			String output_file = input_file.substring(0, input_file.length() - 4);
			output_file = outModelsFolder + trans_module + File.separatorChar + output_file + "_result2" + outMM_name + ".xmi";
			AtlLauncher atl_launcher = new AtlLauncher();
			try {
			    atl_launcher.run(factory, inMetamodel, outMetamodel, injector, inMM_name, entry.getPath(), outMM_name, output_file, asm.getAbsolutePath());
			} catch (Exception e) {
			    System.err.println("Unable to run transformation module against " + entry);
			    e.printStackTrace();
			}
		    }
		}
	    }
	    for (File f : temp.listFiles())
		f.delete();
	    temp.delete();
	}
    }
}
