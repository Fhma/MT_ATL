package uk.ac.york.cs.m2m.atl.model2code.converter;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.engine.parser.AtlParser;

public class AtlModel2Code {

    public static void run(IFile file) throws ATLCoreException, IOException {
	ModelFactory modelFactory = new EMFModelFactory();
	IInjector injector = new EMFInjector();
	IReferenceModel sourceMModel = modelFactory.newReferenceModel();
	injector.inject(sourceMModel, AtlModel2Code.class.getResource("resources/ATL.ecore").openStream(), null);
	IModel sourceModel = modelFactory.newModel(sourceMModel);
	injector.inject(sourceModel, file.getLocationURI().toString());
	String output = file.getLocationURI().toString();
	int len = output.length();
	output = output.substring(0, len - 4) + ".atl";
	new AtlParser().extract(sourceModel, output);
    }

    public static void run(URL file) throws ATLCoreException, IOException {
	ModelFactory modelFactory = new EMFModelFactory();
	IInjector injector = new EMFInjector();
	IReferenceModel sourceMModel = modelFactory.newReferenceModel();
	injector.inject(sourceMModel, AtlModel2Code.class.getResource("resources/ATL.ecore").openStream(), null);
	IModel sourceModel = modelFactory.newModel(sourceMModel);
	injector.inject(sourceModel, file.getFile().toString());
	String output = null;
	output = file.getFile().replace(".xmi", ".atl");
	new AtlParser().extract(sourceModel, output);
    }

    public static void main(String... args) {
	try {
	    File file = new File("transformation/");
	    for (File entry : file.listFiles()) {
		if (entry.getName().endsWith(".xmi")) {
		    run((entry.getAbsoluteFile().toURI()).toURL());
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}