package main.java.raf.dsw.classycraft.app.model.repo.diagram;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;

public abstract class DiagramElement extends ClassyNode {

    public DiagramElement(ClassyNode parent, String ime) {
        super(parent, ime);
    }
}
