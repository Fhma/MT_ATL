package uk.ac.york.cs.m2m.atl.code2model.converter.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import uk.ac.york.cs.m2m.atl.code2model.converter.AtlCode2Model;

public class ModelGenerator extends AbstractHandler {

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
			MessageDialog.openInformation(shell, "Info", "Please select an ATL source file");
		}
		return null;
	}

	private void prepareGeneration(Shell shell, IFile file)
	{
		try
		{
			AtlCode2Model.run(file);
		} catch (Exception e)
		{
			MessageDialog.openError(shell, "Error", "Unable to generate ATL model.\n " + e.getMessage());
		}
	}
}
