package main.java.raf.dsw.classycraft.app.model.repo;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;

public interface ClassyRepository {
    ClassyNode getRoot();

    void addChild(ClassyNode child);

    void removeChild(ClassyNode child);
}
