package uk.ac.york.cs.emu.atl.examples.execution.launcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.logging.Level;

import org.eclipse.m2m.atl.common.ATLLogger;
import org.eclipse.m2m.atl.engine.compiler.atl2006.Atl2006Compiler;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.engine.parser.AtlParser;

import uk.ac.york.cs.emu.atl.examples.execution.transformations.metamodels.EcoreModel;

public class ATLCompiler extends Atl2006Compiler {

    public boolean compileAtlCode(File atlFile, String destFolderPath) {
	if (!atlFile.getAbsolutePath().endsWith(".atl"))
	    return false;

	String asmFile = null;

	if (destFolderPath != null)
	    asmFile = destFolderPath + File.separatorChar + atlFile.getName().substring(0, atlFile.getName().length() - 4) + ".asm";
	else
	    asmFile = atlFile.getAbsolutePath().substring(0, atlFile.getAbsolutePath().length() - 4) + ".asm";

	Atl2006Compiler compiler = new Atl2006Compiler();
	Object[] problems = null;
	ATLLogger.getLogger().setLevel(Level.OFF);

	try {
	    problems = compiler.compile(new FileReader(atlFile), asmFile);
	} catch (FileNotFoundException e) {
	    return false;
	} finally {
	    compiler = null;
	}

	if (problems != null && problems.length > 0)
	    return false;
	return true;
    }

    public boolean compileAtlModel(File atlModel, String destFolderPath) {
	if (!atlModel.getName().endsWith(".xmi"))
	    return false;

	// convert the the model back to code
	try {
	    URL atlURL = atlModel.toURI().toURL();
	    ModelFactory modelFactory = new EMFModelFactory();

	    IInjector injector = new EMFInjector();
	    IReferenceModel sourceMModel = modelFactory.newReferenceModel();
	    injector.inject(sourceMModel, EcoreModel.class.getResource("ATL.ecore").openStream(), null);

	    IModel sourceModel = modelFactory.newModel(sourceMModel);
	    injector.inject(sourceModel, atlURL.getFile().toString());
	    String atlFile = null;
	    if (destFolderPath != null) {
		atlFile = atlModel.getPath();
		atlFile = destFolderPath + atlFile.substring(atlFile.lastIndexOf(File.separatorChar), atlFile.length());
		atlFile = atlFile.replace(".xmi", ".atl");
	    } else
		atlFile = atlURL.getFile();

	    atlFile = atlFile.replace(".xmi", ".atl");
	    new AtlParser().extract(sourceModel, atlFile);
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}

	File atlFile = null;
	if (destFolderPath != null)
	    atlFile = new File(destFolderPath + File.separatorChar + atlModel.getName().replace(".xmi", ".atl"));
	else
	    atlFile = new File(atlModel.getAbsolutePath().replace(".xmi", ".atl"));
	return compileAtlCode(atlFile, destFolderPath);
    }
}
