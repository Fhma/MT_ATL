package uk.ac.york.cs.m2m.atl.code2model.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import org.eclipse.core.resources.IFile;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.emf.EMFExtractor;
import org.eclipse.m2m.atl.engine.parser.AtlParser;

public class AtlCode2Model {

	public static void run(IFile file) throws FileNotFoundException, ATLCoreException {
		if (file.getName().endsWith(".atl")) {
			String fullUri = file.getLocationURI().toString();
			int len = fullUri.length();
			String outputPath = fullUri.substring(0, len - 4) + ".xmi";
			IModel model1 = new AtlParser().parseToModel(new FileInputStream(file.getLocation().toString()));
			new EMFExtractor().extract(model1, outputPath);
		} else {
			throw new IllegalArgumentException(
					"Unsupported Format file. Expected (.atl) and found (." + file.getFileExtension() + ").");
		}
	}

	public static void run(URL file) throws ATLCoreException, IOException {
		if (file.getFile().endsWith(".atl")) {
			String fullUri = file.getFile().toString();
			int len = fullUri.length();
			String outputPath = fullUri.substring(0, len - 4) + ".xmi";
			IModel model1 = new AtlParser().parseToModel(file.openStream());
			File outputXMI = new File(outputPath);
			new EMFExtractor().extract(model1, outputXMI.getAbsolutePath());
		} else {
			throw new IllegalArgumentException("Unsupported Format file. Expected (.atl).");
		}
	}

	public static void main(String... args) {
		try {
			File file = new File("transformation/");
			for(File entry:file.listFiles())
			{
				convertAll(entry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void convertAll(final File entry) {
		if (!entry.isDirectory() && entry.getName().endsWith(".atl")) {
			try {
				run((entry.getAbsoluteFile().toURI()).toURL());
				return;
			} catch (Exception e) {
				// do nothing
			}
		} else if (entry.isDirectory()) {
			for (final File fileEntry : entry.listFiles()) {
				convertAll(fileEntry);
			}
		}
	}
	
}