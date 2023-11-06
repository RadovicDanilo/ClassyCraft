package main.java.raf.dsw.classycraft.app.core;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;

public interface ClassyRepository {
    ProjectExplorer getProjectExplorer();
    void addChild(ClassyNodeComposite parent, ClassyNode child);
}
