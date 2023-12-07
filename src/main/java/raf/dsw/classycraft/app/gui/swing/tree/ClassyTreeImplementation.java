package main.java.raf.dsw.classycraft.app.gui.swing.tree;


import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTree;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;

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
		ApplicationFramework.getInstance().getClassyRepository().addChild(classyNode);
		treeView.expandPath(treeView.getSelectionPath());
		SwingUtilities.updateComponentTreeUI(treeView);
	}
	
	public void removeNode(ClassyTreeItem classyTreeItem) {
		if(classyTreeItem == null)
			return;
		classyTreeItem = getNode(classyTreeItem.getClassyNode());
		if(classyTreeItem == null)
			return;
		
		if(classyTreeItem.getClassyNode() instanceof Diagram) {
			DiagramView dv = new DiagramView((Diagram) classyTreeItem.getClassyNode());
			MainFrame.getInstance().getDiagramViews().remove(dv);
		}
		if(classyTreeItem.getClassyNode() instanceof DiagramElement) {
			for(int i = 0; i < classyTreeItem.getParent().getChildCount(); i++) {
				ClassyTreeItem item = ((ClassyTreeItem) classyTreeItem.getParent().getChildAt(i));
				if(item.getClassyNode() instanceof Connection && (((Connection) item.getClassyNode()).getFrom().equals(classyTreeItem.getClassyNode()) || ((Connection) item.getClassyNode()).getTo().equals(classyTreeItem.getClassyNode()))) {
					removeNode(item);
					i--;
				}
			}
		}
		classyTreeItem.removeFromParent();
		ApplicationFramework.getInstance().getClassyRepository().removeChild(classyTreeItem.getClassyNode());
		treeView.expandPath(treeView.getSelectionPath());
		SwingUtilities.updateComponentTreeUI(treeView);
	}
	
	public ClassyTreeItem getNode(ClassyNode c) {
		//BFS
		ArrayList<ClassyTreeItem> a = new ArrayList<>();
		a.add(root);
		while(a.size() != 0) {
			ArrayList<ClassyTreeItem> b = new ArrayList<>();
			for(ClassyTreeItem classyTreeItem : a) {
				if(classyTreeItem.getClassyNode() == c) {
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
