package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;

import static main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Cardinality.ONE;

public class Aggregation extends Connection {
    private String fieldName;
    private Visibility visibility;
    private Cardinality cardinality;

    public Aggregation(ClassyNodeComposite parent, String name, InterClass from, InterClass to) {
        super(parent, name, from, to);
        fieldName = "name";
        visibility = Visibility.PUBLIC;
        cardinality = ONE;
    }


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Cardinality getCardinality() {
        return cardinality;
    }

    public void setCardinality(Cardinality cardinality) {
        this.cardinality = cardinality;
    }
}
