package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.InterClass;

public class Aggregation extends ConnectionWithField {
    public Aggregation() {
        super();
    }

    public Aggregation(ClassyNodeComposite parent, String name, InterClass from, InterClass to) {
        super(parent, name, from, to);

    }


}
