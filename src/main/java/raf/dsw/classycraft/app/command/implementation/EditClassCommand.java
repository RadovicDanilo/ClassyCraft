package main.java.raf.dsw.classycraft.app.command.implementation;

import main.java.raf.dsw.classycraft.app.command.AbstractCommand;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.ClassContent;

import java.util.ArrayList;

public class EditClassCommand extends AbstractCommand {
    private final Klasa klasa;
    private final String name;
    private final ArrayList<ClassContent> contents;
    private final String oldName;
    private final ArrayList<ClassContent> oldContents;

    public EditClassCommand(Klasa klasa, String name, ArrayList<ClassContent> contents) {
        this.klasa = klasa;
        this.name = name;
        this.contents = contents;
        this.oldName = klasa.getName();
        this.oldContents = (ArrayList<ClassContent>) klasa.getContents().clone();
    }

    @Override
    public void doCommand() {
        klasa.setContents(contents);
        klasa.setNodeName(name);
    }

    @Override
    public void undoCommand() {
        klasa.setContents(oldContents);
        klasa.setNodeName(oldName);
    }
}
