package main.java.raf.dsw.classycraft.app.gui.swing.tree.view;


import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;

public interface ClassyTree {

    ClassyTreeView generateTree(ProjectExplorer projectExplorer);

    void addChild(ClassyTreeItem parent, ClassyNode classyTreeItem);

    ClassyTreeItem getSelectedNode();
}
