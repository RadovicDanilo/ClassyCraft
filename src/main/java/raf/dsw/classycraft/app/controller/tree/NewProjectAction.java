package main.java.raf.dsw.classycraft.app.controller.tree;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.factory.ProjectFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractClassyAction {
	public NewProjectAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		putValue(SMALL_ICON, loadIcon("/images/icons/add_project.png"));
		putValue(NAME, "New project");
		putValue(SHORT_DESCRIPTION, "New project");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ProjectFactory projectFactory = new ProjectFactory();
		ClassyNode classyNode = projectFactory.classyNode();
		MainFrame.getInstance().getClassyTree().addChild(MainFrame.getInstance().getClassyTree().getRoot(), classyNode);
	}
}
