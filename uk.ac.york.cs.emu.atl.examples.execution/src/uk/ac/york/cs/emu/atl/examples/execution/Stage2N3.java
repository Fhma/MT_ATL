package uk.ac.york.cs.emu.atl.examples.execution;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uk.ac.york.cs.emu.atl.examples.execution.mutations.launcher.ATLMutantLauncher;
import uk.ac.york.cs.emu.atl.examples.execution.transformations.launcher.ATLOriginalLauncher;

public class Stage2N3 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		int stage = -1;
		Map<Integer, List<String>> configurations = new HashMap<Integer, List<String>>();
		BufferedReader read = null;
		String line;
		try {
			read = new BufferedReader(new InputStreamReader(
					Stage2N3.class.getResource("configurations/modules.configurations").openStream()));
			while ((line = read.readLine()) != null) {
				if (!line.startsWith("#") && line.length() > 0) {
					if (line.startsWith("$")) {
						String split[] = line.split(" ");
						stage = Integer.parseInt(split[1]);
						configurations.put(stage, new ArrayList<String>());
					} else {
						if (stage != -1)
							configurations.get(stage).add(line);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		Class<?> clazz;
		Method method;
		String _package = Stage2N3.class.getPackage().getName() + ".configurations";
		Map<String, String> config = null;

		// stage = 2;
		if (configurations.get(2) != null) {
			for (String config_file : configurations.get(2)) {
				clazz = Class.forName(_package + "." + config_file);
				method = clazz.getMethod("properties");
				config = (Map<String, String>) method.invoke(clazz);
				System.out.println("Original Transformation Execution: " + config_file);
				ATLOriginalLauncher runner = new ATLOriginalLauncher(config);
				runner.run();
				System.out.println("------------------------------------------------");
			}
		}

		// stage 3
		if (configurations.get(3) != null) {
			ExecutorService executor = null;
			for (String config_file : configurations.get(3)) {
				clazz = Class.forName(_package + "." + config_file);
				method = clazz.getMethod("properties");
				config = (Map<String, String>) method.invoke(clazz);
				System.out.println("Mutations Execution: " + config_file);
				ATLMutantLauncher runner = new ATLMutantLauncher(config);
				// executor = Executors.newSingleThreadExecutor();
				// executor.execute(runner);
				runner.run();
				System.out.println("------------------------------------------------");
			}
			if (executor != null)
				executor.shutdown();
		}
	}
}
