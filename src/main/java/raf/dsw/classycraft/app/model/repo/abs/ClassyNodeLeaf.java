package main.java.raf.dsw.classycraft.app.model.repo.abs;

import main.java.raf.dsw.classycraft.app.model.repo.implementation.NodeType;

public abstract class ClassyNodeLeaf extends ClassyNode{
    public ClassyNodeLeaf(ClassyNode parent, String ime, NodeType type) {
        super(parent, ime, type);
    }
}
