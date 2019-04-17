package uk.ac.york.cs.m2m.atl.model2code.converter.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import uk.ac.york.cs.m2m.atl.model2code.converter.AtlModel2Code;

public class CodeGenerator extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		Shell shell = HandlerUtil.getActiveShell(event);
		ISelection sel = HandlerUtil.getActiveMenuSelection(event);
		IStructuredSelection selection = (IStructuredSelection) sel;

		Object firstElement = selection.getFirstElement();
		if (firstElement instanceof IFile)
		{
			prepareGeneration(shell, (IFile) firstElement);
		}
		else
		{
			MessageDialog.openInformation(shell, "Info", "Please select an ATL model file");
		}
		return null;
	}

	private void prepareGeneration(Shell shell, IFile file)
	{
		try
		{
			AtlModel2Code.run(file);
		} catch (Exception e)
		{
			e.printStackTrace();
			MessageDialog.openError(shell, "Error", "Unable to generate ATL code.\n " + e.getMessage());
		}
	}
}
