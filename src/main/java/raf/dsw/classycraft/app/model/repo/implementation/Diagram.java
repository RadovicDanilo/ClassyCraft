package main.java.raf.dsw.classycraft.app.model.repo.implementation;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeLeaf;

public class Diagram extends ClassyNodeLeaf {
    public Diagram(ClassyNode parent, String ime, NodeType type) {
        super(parent, ime, type);
    }
}
