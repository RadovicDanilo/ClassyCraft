package main.java.raf.dsw.classycraft.app.gui.swing.controller.tree;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.observer.notifications.PackageViewEvent;
import main.java.raf.dsw.classycraft.app.model.observer.notifications.SystemEvent;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.factory.DiagramFactory;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;

import java.awt.event.ActionEvent;
//TODO POPRAVITI OVO KRSENJE MVC-a SA NOTIFY SUBSCRIBER I OSTALIM SRANJIMA
public class NewDiagramAction extends AbstractClassyAction {
	public NewDiagramAction() {
		putValue(SMALL_ICON, loadIcon("/images/icons/add_diagram.png"));
		putValue(NAME, "New diagram");
		putValue(SHORT_DESCRIPTION, "New diagram");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		ClassyTreeItem selectedNode = MainFrame.getInstance().getClassyTree().getSelectedNode();
		if(!(selectedNode.getClassyNode() instanceof Project || selectedNode.getClassyNode() instanceof Package)) {
			ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.DIAGRAM_CAN_ONLY_BE_ADDED_TO_PACKAGE);
			return;
		}
		ClassyNode classyNode;
		int i = 0;
		while(true) {
			DiagramFactory diagramFactory = new DiagramFactory();
			classyNode = diagramFactory.classyNode("diagram " + i, (ClassyNodeComposite) selectedNode.getClassyNode());
			if(!((ClassyNodeComposite) selectedNode.getClassyNode()).getChildren().contains(classyNode)) {
				ApplicationFramework.getInstance().getClassyRepository().addChild(classyNode);
				((Package) classyNode.getParent()).notifySubscribers(PackageViewEvent.ADD_DIAGRAM);
				break;
			}
			i++;
		}
		MainFrame.getInstance().getClassyTree().addChild(selectedNode, classyNode);
	}
}
