package uk.ac.york.cs.emu.atl.examples.execution.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.m2m.atl.common.ATLLogger;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IExtractor;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.emf.EMFExtractor;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.launch.ILauncher;
import org.eclipse.m2m.atl.engine.emfvm.launch.EMFVMUILauncher;

public class AtlLauncher {

    private IModel inModel;
    private IModel outModel;

    private Resource inResource;
    private Resource outResource;

    private ResourceSet currentResourceSet;

    public AtlLauncher() {
    }

    public void run(EMFModelFactory factory, IReferenceModel inMetamodel, IReferenceModel outMetamodel, IInjector injector, String inMMName, String inMPath, String outMMName, String outMPath, String transFile, String helpers) throws ATLCoreException, IOException, RuntimeException {

	// clear any existing resources
	factory.getResourceSet().getResources().clear();

	// load in/out models
	this.inModel = factory.newModel(inMetamodel);
	injector.inject(inModel, inMPath);
	this.outModel = factory.newModel(outMetamodel);

	// prepare a new launcher for this transformation
	final ILauncher launcher = (ILauncher) new EMFVMUILauncher();
	launcher.initialize(Collections.emptyMap());
	launcher.addInModel(inModel, "IN", inMMName);
	launcher.addOutModel(outModel, "OUT", outMMName);

	// Disable ATLLogger
	ATLLogger.getLogger().setLevel(Level.OFF);
	launcher.launch(ILauncher.RUN_MODE, new NullProgressMonitor(), Collections.emptyMap(), (Object[]) getModulesStream(transFile, helpers));

	// save out models
	IExtractor extractor = new EMFExtractor();
	extractor.extract(outModel, outMPath);

	// set resources
	currentResourceSet = factory.getResourceSet();
	inResource = factory.getResourceSet().getResource(URI.createFileURI(inMPath), true);
	outResource = factory.getResourceSet().getResource(URI.createFileURI(outMPath), true);
    }

    public void run(EMFModelFactory factory, IReferenceModel inMetamodel, IReferenceModel outMetamodel, IInjector injector, String inMMName, String inMPath, String outMMName, String outMPath, String asmFile) throws ATLCoreException, IOException, RuntimeException {

	// clear any existing resources
	factory.getResourceSet().getResources().clear();

	// load in/out models
	this.inModel = factory.newModel(inMetamodel);
	injector.inject(inModel, inMPath);
	this.outModel = factory.newModel(outMetamodel);

	// prepare a new launcher for this transformation
	final ILauncher launcher = (ILauncher) new EMFVMUILauncher();
	launcher.initialize(Collections.emptyMap());
	launcher.addInModel(inModel, "IN", inMMName);
	launcher.addOutModel(outModel, "OUT", outMMName);

	// Disable ATLLogger
	ATLLogger.getLogger().setLevel(Level.OFF);

	Object[] streams_array = new Object[1];
	streams_array[0] = new FileInputStream(new File(asmFile));
	
	launcher.launch(ILauncher.RUN_MODE, new NullProgressMonitor(), Collections.emptyMap(), streams_array);
	
	// clear stream
	streams_array = null;
	
	// save out models
	IExtractor extractor = new EMFExtractor();
	extractor.extract(outModel, outMPath);

	// set resources
	currentResourceSet = factory.getResourceSet();
	inResource = factory.getResourceSet().getResource(URI.createFileURI(inMPath), true);
	outResource = factory.getResourceSet().getResource(URI.createFileURI(outMPath), true);
    }

    private InputStream[] getModulesStream(String trans_file, String helpers) throws IllegalArgumentException, IOException {

	if (trans_file == null || (trans_file != null && !trans_file.endsWith(".atl")))
	    throw new IllegalArgumentException("Unable to process ATL file: " + trans_file);

	URL asmFile = getFileURL(new Path(trans_file.substring(0, trans_file.length() - 4) + ".asm").toString());
	List<InputStream> modules = new ArrayList<InputStream>();

	modules.add(asmFile.openStream());

	if (helpers != null) {
	    String helpers_list[] = helpers.split(",");
	    for (int i = 0; i < helpers_list.length; i++) {
		if (helpers_list[i] != null && helpers_list[i].endsWith(".atl")) {
		    asmFile = getFileURL(new Path(helpers_list[i].substring(0, trans_file.length() - 4) + ".asm").toString());
		    modules.add(asmFile.openStream());
		} else
		    throw new IllegalArgumentException("Unrecognise ATL helper file: " + helpers_list[i]);
	    }
	}
	InputStream returned_streams[] = new InputStream[modules.size()];
	for (int i = 0; i < returned_streams.length; i++) {
	    returned_streams[i] = modules.get(i);
	}
	return returned_streams;
    }

    private URL getFileURL(String fileName) throws IOException {
	final URL fileURL = new URL("file://" + fileName);
	return fileURL;
    }

    public Resource getInResource() {
	return inResource;
    }

    public Resource getOutResource() {
	return outResource;
    }

    public ResourceSet getCurrentResourceSet() {
	return currentResourceSet;
    }
}
