package main.java.raf.dsw.classycraft.app.command.implementation;

import main.java.raf.dsw.classycraft.app.command.AbstractCommand;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;

public class DrawEnumFieldCommand extends AbstractCommand {
    private Enum e;
    private String name;

    public DrawEnumFieldCommand(Enum e, String field) {
        this.e = e;
        this.name = field;
    }

    @Override
    public void doCommand() {
        e.addEnum(name);
    }

    @Override
    public void undoCommand() {
        e.getContents().remove(name);
    }
}
