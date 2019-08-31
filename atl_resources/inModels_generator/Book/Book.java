package uk.ac.york.epsilon.emg.model.generator.resources.Book;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.epsilon.emc.emf.EmfMetaModel;
import uk.ac.york.epsilon.emg.model.generator.PropertyKey;

public class Book {
	static private Map<Short, Object> properties;

	public static Map<Short, Object> properties() throws URISyntaxException {
		properties = new HashMap<Short, Object>();

		// meta-variables
		properties.put(PropertyKey.METAMODEL_ALIASE, "Book");
		properties.put(PropertyKey.MAIN_METAMODEL_URI, "Book");
		properties.put(PropertyKey.MAIN_METAMODEL_FILE, Book.class.getResource("Book.ecore").getPath());

		EmfMetaModel h_metamodels[] = null;
		String h_metamodels_path[] = null;
		String h_models_path[] = null;

		properties.put(PropertyKey.HELP_METAMODELS, h_metamodels);
		properties.put(PropertyKey.HELP_METAMODELS_PATH, h_metamodels_path);
		properties.put(PropertyKey.HELP_MODELS_PATH, h_models_path);
		properties.put(PropertyKey.EMG_FILE, Book.class.getResource("generator.emg"));
		properties.put(PropertyKey.OUT_MODEL_FOLDER, "Book");
		properties.put(PropertyKey.OUT_MODEL_FILE, "Book");

		// Model size (number of instances)
		properties.put(PropertyKey.MIN_MODEL_SIZE, 2);

		return properties;
	}

	private Book() {
	}
}
