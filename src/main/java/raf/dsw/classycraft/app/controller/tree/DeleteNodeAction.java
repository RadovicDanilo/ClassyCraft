package main.java.raf.dsw.classycraft.app.controller.tree;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;

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
		ClassyNodeComposite parent = (ClassyNodeComposite) selectedNode.getClassyNode().getParent();
		parent.removeChild(selectedNode.getClassyNode());
		if(selectedNode.getClassyNode() instanceof Diagram) {
			DiagramView dv = new DiagramView((Diagram) selectedNode.getClassyNode());
			MainFrame.getInstance().getDiagramViews().remove(dv);
		}
		
	}
	
	
}
