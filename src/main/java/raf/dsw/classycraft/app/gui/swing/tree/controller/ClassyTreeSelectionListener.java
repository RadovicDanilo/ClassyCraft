package main.java.raf.dsw.classycraft.app.gui.swing.tree.controller;


import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;


public class ClassyTreeSelectionListener implements TreeSelectionListener {
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		TreePath path = e.getPath();
		ClassyTreeItem treeItemSelected = (ClassyTreeItem) path.getLastPathComponent();
		System.out.println("Selektovan cvor:" + treeItemSelected.getClassyNode().getName());
		System.out.println("getPath: " + e.getPath());
	}
}


