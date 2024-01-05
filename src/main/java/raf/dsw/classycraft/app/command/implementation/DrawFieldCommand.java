package main.java.raf.dsw.classycraft.app.command.implementation;

import main.java.raf.dsw.classycraft.app.command.AbstractCommand;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Field;

public class DrawFieldCommand extends AbstractCommand {
    private final Klasa diagramElement;
    private final Field field;

    public DrawFieldCommand(Klasa diagramElement, Field field) {
        this.diagramElement = diagramElement;
        this.field = field;
    }

    @Override
    public void doCommand() {
        diagramElement.addField(field);
    }

    @Override
    public void undoCommand() {
        diagramElement.removeField(field);
    }
}
