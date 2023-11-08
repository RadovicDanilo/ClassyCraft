package main.java.raf.dsw.classycraft.app.model.repo.implementation;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeLeaf;

public class Diagram extends ClassyNodeLeaf {
    protected Diagram(ClassyNode parent, String name) {
        super(parent, name);
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Diagram))
            return false;
        ClassyNode node = (ClassyNode) obj;
        return  super.getParent()==node.getParent() && super.getName().equals(node.getName());
    }
}
