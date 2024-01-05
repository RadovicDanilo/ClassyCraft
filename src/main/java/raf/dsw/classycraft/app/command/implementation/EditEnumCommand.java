package main.java.raf.dsw.classycraft.app.command.implementation;

import main.java.raf.dsw.classycraft.app.command.AbstractCommand;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;

import java.util.ArrayList;

public class EditEnumCommand extends AbstractCommand {
    private final Enum e;
    private final String name;
    private final ArrayList<String> content;
    private final String oldName;
    private final ArrayList<String> oldContent;

    public EditEnumCommand(Enum e, String name, ArrayList<String> content) {
        this.e = e;
        this.name = name;
        this.content = content;
        this.oldName = e.getName();
        this.oldContent = (ArrayList<String>) e.getContents().clone();
    }

    @Override
    public void doCommand() {
        e.setContents(content);
        e.setNodeName(name);
    }

    @Override
    public void undoCommand() {
        e.setContents(oldContent);
        e.setNodeName(oldName);

    }
}
