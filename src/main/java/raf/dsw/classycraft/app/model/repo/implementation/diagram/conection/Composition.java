package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;

import static main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Cardinality.MANY;
import static main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Cardinality.ONE;

public class Composition extends ConnectionWithField {
    public Composition() {
        super();
    }
    public Composition(ClassyNodeComposite parent, String name, InterClass from, InterClass to) {
        super(parent, name, from, to);

    }

}
