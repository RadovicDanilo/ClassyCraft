package main.java.raf.dsw.classycraft.app.model.repo.factory;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;

public class DiagramFactory {
    private static int counter = 0;

    public ClassyNode classyNode(ClassyNodeComposite parent) {
        counter++;
        return new Diagram(parent, "diagram " + counter);
    }
}
