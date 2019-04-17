package uk.ac.york.cs.emu.atl.examples.mutations.generator.configurations;

import java.util.HashMap;
import java.util.Map;

import uk.ac.york.cs.emu.atl.examples.mutations.generator.metamodels.EcoreModel;
import uk.ac.york.cs.emu.atl.examples.mutations.generator.transformations.Module;

public class Template {

	// a hash map to store the values needed for execution of Stage1.java
    static private Map<String, String> properties;

    public static Map<String, String> properties() {
	properties = new HashMap<String, String>();

	// meta-variables
	// name of the module
	String module_name = "Source2Target";
	String helpers_list = null;
	String transformation_code = Module.class.getResource("Source2Target.atl").getPath();
	String transformation_model = Module.class.getResource("Source2Target.xmi").getPath();
	String inMM_name = "Source";
	String inMM_File = EcoreModel.class.getResource("Source.ecore").getPath();
	String outMM_name = "Target";
	String outMM_File = EcoreModel.class.getResource("Target.ecore").getPath();

	// these key value pairs must not be modified
	properties.put("TRANS_MODULE", module_name);
	properties.put("TRANS_CODE", transformation_code);
	properties.put("TRANS_MODEL", transformation_model);
	properties.put("IN_MM_NAME", inMM_name);
	properties.put("IN_MM_FILE", inMM_File);
	properties.put("OUT_MM_NAME", outMM_name);
	properties.put("OUT_MM_FILE", outMM_File);
	properties.put("TRANS_HELPERS", helpers_list);
	return properties;
    }

    private Template() {
    }
}