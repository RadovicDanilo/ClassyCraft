package main.java.raf.dsw.classycraft.app.command.implementation;

import main.java.raf.dsw.classycraft.app.command.AbstractCommand;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Aggregation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Cardinality;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Composition;

public class EditConnectionCommand extends AbstractCommand {
    Connection connection;
    String name;
    Cardinality cardinality;
    Visibility visibility;
    String oldName;
    Cardinality oldCardinality;
    Visibility oldVisibility;

    public EditConnectionCommand(Connection connection, String name, Cardinality cardinality, Visibility visibility) {
        this.connection = connection;
        if (connection instanceof Aggregation) {
            oldName = ((Aggregation) connection).getFieldName();
            oldCardinality = ((Aggregation) connection).getCardinality();
            oldVisibility = ((Aggregation) connection).getVisibility();
        }else if (connection instanceof Composition) {
            oldName = ((Composition) connection).getFieldName();
            oldCardinality = ((Composition) connection).getCardinality();
            oldVisibility = ((Composition) connection).getVisibility();
        }
        this.name = name;
        this.cardinality = cardinality;
        this.visibility = visibility;
    }

    @Override
    public void doCommand() {
        if (connection instanceof Aggregation) {
            ((Aggregation) connection).setFieldName(name);
            ((Aggregation) connection).setCardinality(cardinality);
            ((Aggregation) connection).setVisibility(visibility);
        } else if (connection instanceof Composition) {
            ((Composition) connection).setFieldName(name);
            ((Composition) connection).setCardinality(cardinality);
            ((Composition) connection).setVisibility(visibility);
        }
    }

    @Override
    public void undoCommand() {
        if (connection instanceof Aggregation) {
            ((Aggregation) connection).setFieldName(oldName);
            ((Aggregation) connection).setCardinality(oldCardinality);
            ((Aggregation) connection).setVisibility(oldVisibility);
        } else if (connection instanceof Composition) {
            ((Composition) connection).setFieldName(oldName);
            ((Composition) connection).setCardinality(oldCardinality);
            ((Composition) connection).setVisibility(oldVisibility);
        }

    }
}
