package uk.ac.york.cs.emu.atl.examples.mutations.generator.configurations;

import java.util.HashMap;
import java.util.Map;

import uk.ac.york.cs.emu.atl.examples.mutations.generator.metamodels.EcoreModel;
import uk.ac.york.cs.emu.atl.examples.mutations.generator.transformations.Module;

/**
 * Configuration of the 'Book2Publication' transformation module.
 */
public class Book2Publication {

    static private Map<String, String> properties;

    public static Map<String, String> properties() {
	properties = new HashMap<String, String>();

	// meta-variables
	String module_name = "Book2Publication";
	String helpers_list = null;
	String transformation_code = Module.class.getResource("Book2Publication.atl").getPath();
	String transformation_model = Module.class.getResource("Book2Publication.xmi").getPath();
	String inMM_name = "Book";
	String inMM_File = EcoreModel.class.getResource("Book.ecore").getPath();
	String outMM_name = "Publication";
	String outMM_File = EcoreModel.class.getResource("Publication.ecore").getPath();

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

    private Book2Publication() {
    }
}