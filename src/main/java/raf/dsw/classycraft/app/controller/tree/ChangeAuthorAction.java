package main.java.raf.dsw.classycraft.app.controller.tree;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChangeAuthorAction extends AbstractClassyAction {
	public ChangeAuthorAction() {
		putValue(SMALL_ICON, loadIcon("/images/icons/user_plus.png"));
		putValue(NAME, "Change author");
		putValue(SHORT_DESCRIPTION, "Change author");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(MainFrame.getInstance().getClassyTree().getSelectedNode() == null) {
			ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.NO_SELECTED_NODE);
			return;
		}
		ClassyNode classyNode = MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
		if(classyNode == null) {
			ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.CHANGE_AUTHOR_CAN_ONLY_BE_PREFORMED_ON_PROJECTS);
			return;
		}
		if(!(classyNode instanceof Project)) {
			ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.CHANGE_AUTHOR_CAN_ONLY_BE_PREFORMED_ON_PROJECTS);
			return;
		}
		String author = JOptionPane.showInputDialog(null, "Ime autora");
		((Project) classyNode).setAuthor(author);
	}
}
