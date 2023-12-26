package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.InterClass;

import static main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Cardinality.MANY;
import static main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Cardinality.ONE;

public class Aggregation extends ConnectionWithField {


    public Aggregation(ClassyNodeComposite parent, String name, InterClass from, InterClass to) {
        super(parent, name, from, to);

    }


}
