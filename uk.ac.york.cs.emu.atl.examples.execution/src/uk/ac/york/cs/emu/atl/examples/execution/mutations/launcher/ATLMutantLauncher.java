package uk.ac.york.cs.emu.atl.examples.execution.mutations.launcher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import uk.ac.york.cs.emu.atl.examples.execution.launcher.ATLCompiler;

public class ATLMutantLauncher {

	private static final short WAIT_REPEAT = 10;
	private static final short WAIT_TIME = 2000;
	private static final short RESULT_WAITING_TIME = 10;

	private String trans_module;
	private String inMM_name;
	private String inMM_File;
	private String outMM_name;
	private String outMM_File;
	private short exe_time;

	public ATLMutantLauncher(Map<String, String> config) {
		trans_module = config.get("TRANS_MODULE");
		inMM_name = config.get("IN_MM_NAME");
		inMM_File = config.get("IN_MM_FILE");
		outMM_name = config.get("OUT_MM_NAME");
		outMM_File = config.get("OUT_MM_FILE");
		exe_time = Short.parseShort(config.get("EXE_TIME"));
	}

	public void run() throws InterruptedException {

		// Folders path initialisation
		// input models
		String inMDir = "inModels" + File.separatorChar;

		Logger.getRootLogger().setLevel(Level.OFF);

		// File mutants_folder = new File("generatedMutations" +
		// File.separatorChar + trans_module);
		File mutants_folder = new File("generatedMutations" + File.separatorChar + trans_module);
		File input_folder = new File(inMDir + trans_module);

		// Initialise execution output folder
		File mutations_execution = new File("mutationsExecution" + File.separatorChar + trans_module);
		mutations_execution.mkdirs();

		List<OperatorEntry> operators_stats = new ArrayList<OperatorEntry>();

		File[] mutants = mutants_folder.listFiles();
		Arrays.sort(mutants);

		// Going through all mutants
		for (File mutant_model : mutants) {
			if (mutant_model.isFile() && mutant_model.getName().endsWith("xmi")) {

				OperatorEntry current_operator = getOperatorEntryByMutant(operators_stats, mutant_model.getName());
				if (current_operator == null) {
					current_operator = new OperatorEntry(mutant_model);
					operators_stats.add(current_operator);
				}

				current_operator.incrementProcessed();

				// create temporary folder to hold mutant execution
				// output (for comparison later)
				File temp_outputs = new File(mutations_execution.getPath() + File.separatorChar + "temp");
				temp_outputs.mkdirs();
				temp_outputs.deleteOnExit();

				System.out.println("Mutant: " + mutant_model);
				ATLCompiler compiler = new ATLCompiler();
				// Does the mutant compile
				boolean valid = false;
				while (true) {

					try {
						compiler.compileAtlModel(mutant_model, temp_outputs.getAbsolutePath());
					} catch (Exception | Error e) {
						valid = false;
						break;
					}

					// obtain the compiled file of the mutant
					File asm = new File(temp_outputs.getPath() + File.separatorChar
							+ mutant_model.getName().substring(0, mutant_model.getName().length() - 4) + ".asm");
					asm.deleteOnExit();
					if (!asm.exists()) {
						valid = false;
						break;
					}
					valid = true;

					// read input models for transformation (mutant)
					// execution
					ExecutorService executor = Executors.newSingleThreadExecutor();
					List<Future<String>> futures = new ArrayList<Future<String>>();

					short total_tests = 0;
					File input_models[] = input_folder.listFiles();
					for (int j = 0; j < input_models.length; j++) {
						if (input_models[j].getName().endsWith(".xmi")) {
							total_tests++;
							ATLMutantExecutor exe = new ATLMutantExecutor(trans_module, inMM_name, inMM_File,
									outMM_name, outMM_File, input_models[j], asm, temp_outputs);
							Future<String> f = executor.submit(exe);
							futures.add(f);
						}
					}

					short attempts = WAIT_REPEAT;
					while (attempts >= 1) {
						short done = 0;
						for (Future<String> f : futures) {
							if (f.isDone())
								done++;
						}
						if (done == total_tests)
							break;
						else
							Thread.sleep(exe_time);
						attempts--;
					}

					short total_killed = 0;
					for (Future<String> f : futures) {
						try {
							String res = f.get(RESULT_WAITING_TIME, TimeUnit.MILLISECONDS);
							if (res.equals(ATLMutantExecutor.KILLED)) // killed
								total_killed++;
						} catch (TimeoutException e) {
							total_killed++;
							f.cancel(true);
						} catch (Exception | Error e) {
							e.printStackTrace();
						}
					}

					executor.shutdownNow();

					attempts = WAIT_REPEAT;
					while (!executor.isTerminated()) {
						if (attempts == 0) {
							System.err.println("unable to terminate mutant " + mutant_model.getName());
							break;
						}
						Thread.sleep(WAIT_TIME);
						attempts--;
						executor.shutdownNow();
					}

					// clear execution temporary files
					if (temp_outputs != null && temp_outputs.isDirectory()) {
						for (File f : temp_outputs.listFiles())
							f.delete();
					}

					if (total_killed == 0) {
						getOperatorEntryByMutant(operators_stats, mutant_model.getName())
								.addNotKilled(mutant_model.getName());
					} else if (total_killed == total_tests) {
						// trivial
						getOperatorEntryByMutant(operators_stats, mutant_model.getName()).incrementTrivial();
					} else {
						// just killed
						getOperatorEntryByMutant(operators_stats, mutant_model.getName()).incrementKilled();
					}
					break;
				}
				if (!valid) {
					// unable to compile the mutant and therefore invalid
					getOperatorEntryByMutant(operators_stats, mutant_model.getName()).incrementInvalid();
				}
			}
		}

		try {
			print_summary(operators_stats, mutations_execution.getAbsolutePath());
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	private OperatorEntry getOperatorEntryByMutant(List<OperatorEntry> list, String mutant) {
		String oid = mutant.substring(0, mutant.lastIndexOf("_"));
		for (int j = 0; j < list.size(); j++)
			if (list.get(j).getOID().equals(oid))
				return list.get(j);
		return null;
	}

	private void print_summary(List<OperatorEntry> list, String path) throws IOException {
		if (list == null)
			return;

		Iterator<OperatorEntry> it = list.iterator();
		StringBuilder entry = new StringBuilder();

		entry.append("Mutation Operator");
		entry.append(",");
		entry.append("Gen.");
		entry.append(",");
		entry.append("Killed");
		entry.append(",");
		entry.append("Trivial");
		entry.append(",");
		entry.append("Not Killed");
		entry.append(",");
		entry.append("Invalid");
		entry.append("\n");

		int total_processed = 0, total_killed = 0, total_trivial = 0, total_not_killed = 0, total_invalid = 0;

		String notKilled_string = "";

		while (it.hasNext()) {

			OperatorEntry operator = it.next();
			entry.append(operator.getOID());
			entry.append(',');

			entry.append(operator.getTotalProcessedMutants());
			entry.append(',');
			total_processed += operator.getTotalProcessedMutants();

			entry.append(operator.getTotalKilledMutants());
			entry.append(',');
			total_killed += operator.getTotalKilledMutants();

			entry.append(operator.getTotalTrivialMutants());
			entry.append(',');
			total_trivial += operator.getTotalTrivialMutants();

			entry.append(operator.getNotKilledMutants().size());
			entry.append(',');
			total_not_killed += operator.getNotKilledMutants().size();
			for (String live : operator.getNotKilledMutants())
				notKilled_string += "\n\t\t\\-->" + live;

			entry.append(operator.getTotalInvalidMutants());
			entry.append('\n');
			total_invalid += operator.getTotalInvalidMutants();

		}

		entry.append("Total");
		entry.append(",");
		entry.append(total_processed);
		entry.append(",");
		entry.append(total_killed);
		entry.append(",");
		entry.append(total_trivial);
		entry.append(",");
		entry.append(total_not_killed);
		entry.append(",");
		entry.append(total_invalid);
		entry.append("\n");

		try (FileWriter log = new FileWriter(path + File.separatorChar + trans_module + ".txt")) {

			log.write("\t\\--> Processed mutants-> " + total_processed + "\n");
			log.write("\t\\--> Invalid mutants-> " + total_invalid + "\n");
			log.write("\t\\--> Killed mutants-> " + total_killed + "\n");
			log.write("\t\\--> Not killed mutants-> " + total_not_killed);
			log.write(notKilled_string + "\n");
			log.flush();
			log.close();
		}

		try (FileWriter file = new FileWriter(path + File.separatorChar + trans_module + ".csv")) {
			file.write(entry.toString());
			file.flush();
			file.close();
		}
	}
}
