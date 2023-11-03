package main.java.raf.dsw.classycraft.app.model.repo.implementation;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;

public class Package extends ClassyNodeComposite {
    public Package(ClassyNode parent, String ime, NodeType type) {
        super(parent, ime, type);
    }
}
