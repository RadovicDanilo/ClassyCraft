package main.java.raf.dsw.classycraft.app.model.repo.diagram.conection;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass.InterClass;

public class Dependency extends Concection{
    public Dependency(ClassyNode parent, String ime, InterClass from, InterClass to) {
        super(parent, ime, from, to);
    }
}
