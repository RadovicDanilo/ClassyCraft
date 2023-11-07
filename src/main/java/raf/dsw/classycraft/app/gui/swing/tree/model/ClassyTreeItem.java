package main.java.raf.dsw.classycraft.app.gui.swing.tree.model;


import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;

import javax.swing.tree.DefaultMutableTreeNode;

public class ClassyTreeItem extends DefaultMutableTreeNode {

    private ClassyNode classyNode;

    public ClassyTreeItem(ClassyNode classyNode) {
        this.classyNode = classyNode;
    }
    public void remove(){

        //obrisi this

        //Obrisis this.classynode
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
