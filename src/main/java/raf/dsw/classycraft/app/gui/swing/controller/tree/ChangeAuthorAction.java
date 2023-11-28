package main.java.raf.dsw.classycraft.app.gui.swing.controller.tree;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.observer.notifications.PackageViewEvent;
import main.java.raf.dsw.classycraft.app.model.observer.notifications.SystemEvent;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ChangeAuthorAction extends AbstractClassyAction {
	public ChangeAuthorAction() {
		putValue(SMALL_ICON, loadIcon("/images/icons/user_plus.png"));
		putValue(NAME, "Change author");
		putValue(SHORT_DESCRIPTION, "Change author");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(MainFrame.getInstance().getClassyTree().getSelectedNode() == null) {
			ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.CHANGE_AUTHOR_CAN_ONLY_BE_PREFORMED_ON_PROJECTS);
			return;
		}
		ClassyNode classyNode = MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
		if(classyNode instanceof Project) {
			String author = JOptionPane.showInputDialog(null, "Ime autora");
			Project project = (Project) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
			System.out.println("Projekat " + project.getName() + " Prethodni autor: " + project.getAuthor());
			project.setAuthor(author);
			
			changeAuthorUpdate((ArrayList<ClassyNode>) project.getChildren());
			
			System.out.println("Projekat " + project.getName() + " Novi autor: " + project.getAuthor());
		}else {
			ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.CHANGE_AUTHOR_CAN_ONLY_BE_PREFORMED_ON_PROJECTS);
		}
	}
	
	public void changeAuthorUpdate(ArrayList<ClassyNode> children) {
		for(ClassyNode classyNode : children) {
			if(classyNode instanceof Package) {
				((Package) classyNode).notifySubscribers(PackageViewEvent.CHANGE_AUTHOR);
				changeAuthorUpdate((ArrayList<ClassyNode>) ((Package) classyNode).getChildren());
			}
		}
	}
}
