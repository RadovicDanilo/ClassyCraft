package main.java.raf.dsw.classycraft.app.gui.swing.tree.view;


import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Agregation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Composition;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Dependency;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Generalisation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class ClassyTreeCellRenderer extends DefaultTreeCellRenderer {
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		URL imageURL = null;
		
		if(((ClassyTreeItem) value).getClassyNode() instanceof ProjectExplorer) {
			imageURL = getClass().getResource("/images/icons/project_explorer.png");
		}
		if(((ClassyTreeItem) value).getClassyNode() instanceof Project) {
			imageURL = getClass().getResource("/images/icons/project.png");
		}
		if(((ClassyTreeItem) value).getClassyNode() instanceof Package) {
			imageURL = getClass().getResource("/images/icons/package.png");
		}
		if(((ClassyTreeItem) value).getClassyNode() instanceof Diagram) {
			imageURL = getClass().getResource("/images/icons/diagram.png");
		}
		
		if(((ClassyTreeItem) value).getClassyNode() instanceof Klasa) {
			imageURL = getClass().getResource("/images/icons/c.png");
		}
		if(((ClassyTreeItem) value).getClassyNode() instanceof Enum) {
			imageURL = getClass().getResource("/images/icons/e.png");
		}
		if(((ClassyTreeItem) value).getClassyNode() instanceof Interface) {
			imageURL = getClass().getResource("/images/icons/i.png");
		}
		
		if(((ClassyTreeItem) value).getClassyNode() instanceof Generalisation) {
			imageURL = getClass().getResource("/images/icons/gener.png");
		}
		if(((ClassyTreeItem) value).getClassyNode() instanceof Agregation) {
			imageURL = getClass().getResource("/images/icons/agr.png");
		}
		if(((ClassyTreeItem) value).getClassyNode() instanceof Composition) {
			imageURL = getClass().getResource("/images/icons/comp.png");
		}
		if(((ClassyTreeItem) value).getClassyNode() instanceof Dependency) {
			imageURL = getClass().getResource("/images/icons/dep.png");
		}
		Icon icon = null;
		if(imageURL != null)
			icon = new ImageIcon(imageURL);
		setIcon(icon);
		
		return this;
	}
	
}


