package main.java.raf.dsw.classycraft.app.controller.tree;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

import java.awt.event.ActionEvent;

public class DeleteNodeAction extends AbstractClassyAction {
	public DeleteNodeAction() {
		putValue(SMALL_ICON, loadIcon("/images/icons/remove_project.png"));
		putValue(NAME, "Delete node");
		putValue(SHORT_DESCRIPTION, "Delete node");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ClassyTreeItem selectedNode = MainFrame.getInstance().getClassyTree().getSelectedNode();
		if(selectedNode.getClassyNode() == null) {
			ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.NO_SELECTED_NODE);
			return;
		}
		if(selectedNode.getClassyNode() instanceof ProjectExplorer) {
			ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.CANNOT_REMOVE_ROOT);
			return;
		}
		MainFrame.getInstance().getClassyTree().removeNode(selectedNode);
		
	}
	
	
}
