package main.java.raf.dsw.classycraft.app.command.implementation;

import main.java.raf.dsw.classycraft.app.command.AbstractCommand;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Method;

import java.util.ArrayList;

public class EditInterfaceCommand extends AbstractCommand {
    private final Interface interfejs;
    private final String name;
    private final String oldName;
    private final ArrayList<Method> methods;
    private final ArrayList<Method> oldMethods;

    public EditInterfaceCommand(Interface interfejs, String name, ArrayList<Method> methods) {
        this.interfejs = interfejs;
        this.name = name;
        this.methods = methods;
        this.oldName = interfejs.getName();
        this.oldMethods = interfejs.getMethods();
    }

    @Override
    public void doCommand() {
        interfejs.setNodeName(name);
        interfejs.setMethods(methods);
    }

    @Override
    public void undoCommand() {
        interfejs.setNodeName(oldName);
        interfejs.setMethods(oldMethods);
    }
}
