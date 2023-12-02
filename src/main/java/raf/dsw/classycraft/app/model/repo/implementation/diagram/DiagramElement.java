package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeLeaf;

public abstract class DiagramElement extends ClassyNodeLeaf {

    public DiagramElement(ClassyNodeComposite parent, String name) {
        super(parent, name);
    }
}
