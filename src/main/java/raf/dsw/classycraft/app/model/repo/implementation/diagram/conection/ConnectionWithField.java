package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.InterClass;

import static main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Cardinality.MANY;
import static main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Cardinality.ONE;

public class ConnectionWithField extends Connection {
    private String fieldName;
    private Visibility visibility;
    private Cardinality cardinality;

    public ConnectionWithField() {
        super();
    }

    public ConnectionWithField(ClassyNodeComposite parent, String name, InterClass from, InterClass to) {
        super(parent, name, from, to);
        fieldName = "name" + getField();
        setField(getField() + 1);
        visibility = Visibility.PRIVATE;
        cardinality = ONE;
    }

    public String toCode() {
        String value = getTo().getName();
        if (cardinality == MANY) {
            switch (value) {
                case "int":
                    value = "Integer";
                    break;
                case "float":
                    value = "Float";
                    break;
                case "double":
                    value = "Double";
                    break;
                case "boolean":
                    value = "Boolean";
                    break;
                default:
            }
            value = "List<" + value + ">";
        }
        return "\t" + visibility.toCode() + " " + value + " " + fieldName + ";";
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
