package main.java.raf.dsw.classycraft.app.command.implementation;

import main.java.raf.dsw.classycraft.app.command.AbstractCommand;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.ClassContent;

import java.util.ArrayList;

public class EditClassCommand extends AbstractCommand {
    private Klasa klasa;
    private String name;
    private ArrayList<ClassContent> contents;
    private String oldName;
    private ArrayList<ClassContent> oldContents;

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
        klasa.setName(name);
    }

    @Override
    public void undoCommand() {
        klasa.setContents(oldContents);
        klasa.setName(oldName);
    }
}
