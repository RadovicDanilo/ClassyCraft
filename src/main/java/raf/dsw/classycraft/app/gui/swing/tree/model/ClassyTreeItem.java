package main.java.raf.dsw.classycraft.app.gui.swing.tree.model;


import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Objects;

public class ClassyTreeItem extends DefaultMutableTreeNode {
	
	private ClassyNode classyNode;
	
	public ClassyTreeItem(ClassyNode classyNode) {
		this.classyNode = classyNode;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		ClassyTreeItem that = (ClassyTreeItem) o;
		return Objects.equals(classyNode, that.classyNode);
	}
	
	@Override
	public String toString() {
		return classyNode.getName();
	}
	
	public ClassyNode getClassyNode() {
		return classyNode;
	}
	
	public void setClassyNode(ClassyNode classyNode) {
		this.classyNode = classyNode;
	}
	
	public void setName(String name) {
		classyNode.setName(name);
	}
	
	public String getName() {
		return classyNode.getName();
	}
	
}
