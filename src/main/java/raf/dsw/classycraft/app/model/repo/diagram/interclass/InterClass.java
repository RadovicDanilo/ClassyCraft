package main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.Visibility;

public class InterClass extends DiagramElement {
    private Visibility visibility;
    public InterClass(ClassyNode parent, String ime, Visibility visibility) {
        super(parent, ime);
        this.visibility =visibility;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }
}
