package main.java.raf.dsw.classycraft.app.command.implementation;

import main.java.raf.dsw.classycraft.app.command.AbstractCommand;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Cardinality;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.ConnectionWithField;

public class EditConnectionCommand extends AbstractCommand {
    ConnectionWithField connection;
    String name;
    Cardinality cardinality;
    Visibility visibility;
    String oldName;
    Cardinality oldCardinality;
    Visibility oldVisibility;

    public EditConnectionCommand(ConnectionWithField connection, String name, Cardinality cardinality, Visibility visibility) {
        this.connection = connection;
        oldName = connection.getFieldName();
        oldCardinality = connection.getCardinality();
        oldVisibility = connection.getVisibility();

        this.name = name;
        this.cardinality = cardinality;
        this.visibility = visibility;
    }

    @Override
    public void doCommand() {
        connection.setFieldName(name);
        connection.setCardinality(cardinality);
        connection.setVisibility(visibility);

    }

    @Override
    public void undoCommand() {
        connection.setFieldName(oldName);
        connection.setCardinality(oldCardinality);
        connection.setVisibility(oldVisibility);


    }
}
