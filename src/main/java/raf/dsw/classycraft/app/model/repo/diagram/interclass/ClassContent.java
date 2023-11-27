package main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.Visibility;

public abstract class ClassContent extends InterClass {
    public ClassContent(ClassyNode parent, String ime, Visibility visibility) {
        super(parent, ime, visibility);
    }
}
