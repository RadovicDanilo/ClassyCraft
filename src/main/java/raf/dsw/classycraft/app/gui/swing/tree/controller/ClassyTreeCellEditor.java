package main.java.raf.dsw.classycraft.app.gui.swing.tree.controller;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.model.observer.notifications.PackageViewEvent;
import main.java.raf.dsw.classycraft.app.model.observer.notifications.SystemEvent;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;

public class ClassyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {
	private Object clickedOn = null;
	
	public ClassyTreeCellEditor(JTree jTree, DefaultTreeCellRenderer defaultTreeCellRenderer) {
		super(jTree, defaultTreeCellRenderer);
	}
	
	public Component getTreeCellEditorComponent(JTree jTree, Object clickedOn, boolean isSelected, boolean expanded, boolean leaf, int row) {
		super.getTreeCellEditorComponent(jTree, clickedOn, isSelected, expanded, leaf, row);
		this.clickedOn = clickedOn;
		JTextField edit = new JTextField(clickedOn.toString());
		edit.addActionListener(this);
		return edit;
	}
	
	public boolean isCellEditable(EventObject eventObject) {
		if(eventObject instanceof MouseEvent)
			return ((MouseEvent) eventObject).getClickCount() == 3;
		return false;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(!(clickedOn instanceof ClassyTreeItem))
			return;
		if(((ClassyTreeItem) clickedOn).getClassyNode() instanceof ProjectExplorer){
			//TODO sys event cannot rename root;
			return;
		}
		ClassyTreeItem clicked = (ClassyTreeItem) clickedOn;
		clicked.setName(e.getActionCommand());
	}
	
	
}
