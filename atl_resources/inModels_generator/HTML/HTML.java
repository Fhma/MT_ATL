package uk.ac.york.epsilon.emg.model.generator.resources.HTML;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.epsilon.emc.emf.EmfMetaModel;
import uk.ac.york.epsilon.emg.model.generator.PropertyKey;

public class HTML {

	static private Map<Short, Object> properties;

	public static Map<Short, Object> properties() {
		properties = new HashMap<Short, Object>();
		// meta-variables
		properties.put(PropertyKey.METAMODEL_ALIASE, "HTML");
		properties.put(PropertyKey.MAIN_METAMODEL_URI, "HTML");
		properties.put(PropertyKey.MAIN_METAMODEL_FILE, HTML.class.getResource("HTML.ecore").getPath());
		EmfMetaModel h_metamodels[] = null;
		String h_metamodels_path[] = null;
		String h_models_path[] = null;
		properties.put(PropertyKey.HELP_METAMODELS, h_metamodels);
		properties.put(PropertyKey.HELP_METAMODELS_PATH, h_metamodels_path);
		properties.put(PropertyKey.HELP_MODELS_PATH, h_models_path);
		properties.put(PropertyKey.EMG_FILE, HTML.class.getResource("generator.emg"));
		properties.put(PropertyKey.OUT_MODEL_FOLDER, "HTML");
		properties.put(PropertyKey.OUT_MODEL_FILE, "HTML");
		properties.put(PropertyKey.MIN_MODEL_SIZE, 4);
		return properties;
	}
}
