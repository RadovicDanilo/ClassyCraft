package main.java.raf.dsw.classycraft.app.model.repo.implementation;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite {
    public ProjectExplorer(String ime) {
        super(null, ime, NodeType.PROJECT_EXPLORER);
    }
}
