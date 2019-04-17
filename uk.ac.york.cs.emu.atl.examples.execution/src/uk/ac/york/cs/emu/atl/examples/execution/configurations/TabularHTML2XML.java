package uk.ac.york.cs.emu.atl.examples.execution.configurations;

import java.util.HashMap;
import java.util.Map;

import uk.ac.york.cs.emu.atl.examples.execution.transformations.Module;
import uk.ac.york.cs.emu.atl.examples.execution.transformations.metamodels.EcoreModel;

/**
 * Configuration of the 'TabularHTML2XML' transformation module.
 */
public class TabularHTML2XML {

	static private Map<String, String> properties;

	public static Map<String, String> properties() {
		properties = new HashMap<String, String>();

		// meta-variables
		String module_name = "TabularHTML2XML";
		String helpers_list = null;
		String transformation_code = Module.class.getResource("TabularHTML2XML.atl").getPath();
		String transformation_model = Module.class.getResource("TabularHTML2XML.xmi").getPath();
		String inMM_name = "HTML";
		String inMM_File = EcoreModel.class.getResource("HTML.ecore").getPath();
		String outMM_name = "XML";
		String outMM_File = EcoreModel.class.getResource("XML.ecore").getPath();

		properties.put("TRANS_MODULE", module_name);
		properties.put("TRANS_CODE", transformation_code);
		properties.put("TRANS_MODEL", transformation_model);
		properties.put("IN_MM_NAME", inMM_name);
		properties.put("IN_MM_FILE", inMM_File);
		properties.put("OUT_MM_NAME", outMM_name);
		properties.put("OUT_MM_FILE", outMM_File);
		properties.put("TRANS_HELPERS", helpers_list);
		properties.put("EXE_TIME", "2500");
		return properties;
	}

	private TabularHTML2XML() {
	}
}