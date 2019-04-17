package uk.ac.york.cs.emu.atl.examples.mutations.generator.configurations;

import java.util.HashMap;
import java.util.Map;

import uk.ac.york.cs.emu.atl.examples.mutations.generator.metamodels.EcoreModel;
import uk.ac.york.cs.emu.atl.examples.mutations.generator.transformations.Module;

/**
 * Configuration of the 'Table2TabularHTML' transformation module.
 */
public class Table2TabularHTML {

    static private Map<String, String> properties;

    public static Map<String, String> properties() {
	properties = new HashMap<String, String>();

	// meta-variables
	String module_name = "Table2TabularHTML";
	String helpers_list = null;
	String transformation_code = Module.class.getResource("Table2TabularHTML.atl").getPath();
	String transformation_model = Module.class.getResource("Table2TabularHTML.xmi").getPath();
	String inMM_name = "Table";
	String inMM_File = EcoreModel.class.getResource("Table.ecore").getPath();
	String outMM_name = "HTML";
	String outMM_File = EcoreModel.class.getResource("HTML.ecore").getPath();

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

    private Table2TabularHTML() {
    }
}