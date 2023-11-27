package main.java.raf.dsw.classycraft.app.gui.swing.controller.tree;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.observer.notifications.SystemEvent;
import main.java.raf.dsw.classycraft.app.model.observer.notifications.PackageViewEvent;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class DeleteNodeAction extends AbstractClassyAction{
	public DeleteNodeAction(){
		putValue(SMALL_ICON, loadIcon("/images/icons/remove_project.png"));
		putValue(NAME, "Delete node");
		putValue(SHORT_DESCRIPTION, "Delete node");
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		ClassyTreeItem selectedNode = MainFrame.getInstance().getClassyTree().getSelectedNode();
		
		if(selectedNode.getClassyNode() instanceof ProjectExplorer){
			ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.CANNOT_REMOVE_ROOT);
			return;
		}
		
		ClassyNodeComposite parent = (ClassyNodeComposite) selectedNode.getClassyNode().getParent();
		parent.removeChild(selectedNode.getClassyNode());
		
		if(selectedNode.getClassyNode() instanceof Diagram){
			((Package) parent).notifySubscribers(PackageViewEvent.REMOVE_DIAGRAM);
			
		}else if(selectedNode.getClassyNode() instanceof Package){
			((Package) selectedNode.getClassyNode()).notifySubscribers(PackageViewEvent.REMOVE_ALL);
			
		}else{
			deleteProjectUpdate((ArrayList<ClassyNode>) ((Project) selectedNode.getClassyNode()).getChildren());
		}
		
		((ProjectExplorer) ApplicationFramework.getInstance().getClassyRepository().getRoot()).removeChild(selectedNode.getClassyNode());
		(ApplicationFramework.getInstance().getClassyRepository()).removeChild(selectedNode.getClassyNode());
		((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).removeNode(selectedNode);
	}
	
	public void deleteProjectUpdate(ArrayList<ClassyNode> children){
		for(ClassyNode classyNode : children){
			if(classyNode instanceof Package){
				((Package) classyNode).notifySubscribers(PackageViewEvent.REMOVE_ALL);
				deleteProjectUpdate((ArrayList<ClassyNode>) ((Package) classyNode).getChildren());
			}
		}
	}
}
