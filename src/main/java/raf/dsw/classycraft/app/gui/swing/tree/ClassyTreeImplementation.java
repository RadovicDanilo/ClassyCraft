package main.java.raf.dsw.classycraft.app.gui.swing.tree;


import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTree;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;

public class ClassyTreeImplementation implements ClassyTree {
	private ClassyTreeView treeView;
	private ClassyTreeItem root;
	
	@Override
	public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
		root = new ClassyTreeItem(projectExplorer);
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		treeView = new ClassyTreeView(treeModel);
		return treeView;
	}
	
	@Override
	public void addChild(ClassyTreeItem parent, ClassyNode classyNode) {
		parent.add(new ClassyTreeItem(classyNode));
		treeView.expandPath(treeView.getSelectionPath());
		SwingUtilities.updateComponentTreeUI(treeView);
	}
	
	public void removeNode(ClassyTreeItem classyTreeItem) {
		if(getNode(classyTreeItem.getClassyNode()) == null)
			return;
		getNode(classyTreeItem.getClassyNode()).removeFromParent();
		treeView.expandPath(treeView.getSelectionPath());
		SwingUtilities.updateComponentTreeUI(treeView);
	}
	public ClassyTreeItem getNode(ClassyNode c){
		ArrayList<ClassyTreeItem > a = new ArrayList<>();
		a.add(root);
		while(a.size() != 0){
			ArrayList<ClassyTreeItem > b = new ArrayList<>();
			for(ClassyTreeItem classyTreeItem: a){
				if(classyTreeItem.getClassyNode() == c){
					return classyTreeItem;
				}
				for(int i = 0; i < classyTreeItem.getChildCount(); i++) {
					b.add((ClassyTreeItem) classyTreeItem.getChildAt(i));
				}
			}
			a = b;
		}
		return null;
	}
	@Override
	public ClassyTreeItem getSelectedNode() {
		return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
	}
	
	public ClassyTreeItem getRoot() {
		return root;
	}
	
}
