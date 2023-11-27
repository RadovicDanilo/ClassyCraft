package main.java.raf.dsw.classycraft.app.gui.swing.tree.view;


import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class ClassyTreeCellRenderer extends DefaultTreeCellRenderer{
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus){
		
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		URL imageURL = null;
		
		if(((ClassyTreeItem) value).getClassyNode() instanceof ProjectExplorer){
			imageURL = getClass().getResource("/images/icons/project_explorer.png");
		}
		if(((ClassyTreeItem) value).getClassyNode() instanceof Project){
			imageURL = getClass().getResource("/images/icons/project.png");
		}
		if(((ClassyTreeItem) value).getClassyNode() instanceof Package){
			imageURL = getClass().getResource("/images/icons/package.png");
		}
		if(((ClassyTreeItem) value).getClassyNode() instanceof Diagram){
			imageURL = getClass().getResource("/images/icons/diagram.png");
		}
		Icon icon = null;
		if(imageURL != null)
			icon = new ImageIcon(imageURL);
		setIcon(icon);
		
		return this;
	}
	
}


