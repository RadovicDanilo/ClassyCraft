package main.java.raf.dsw.classycraft.app.model.repo;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;

public interface ClassyRepository {
    ClassyNode getRoot();
    void addChild(ClassyNode child);
    void removeChild(ClassyNode child);
}
