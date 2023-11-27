package main.java.raf.dsw.classycraft.app.model.repo.diagram.conection;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass.InterClass;

public abstract class Concection extends DiagramElement {

    private InterClass from;
    private InterClass to;
    public Concection(ClassyNode parent, String ime,InterClass from, InterClass to) {
        super(parent, ime);
        this.from = from;
        this.to = to;
    }
}
