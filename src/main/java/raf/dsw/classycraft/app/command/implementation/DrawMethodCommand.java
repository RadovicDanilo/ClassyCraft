package main.java.raf.dsw.classycraft.app.command.implementation;

import main.java.raf.dsw.classycraft.app.command.AbstractCommand;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Method;

public class DrawMethodCommand extends AbstractCommand {
    private InterClass interClass;
    private Method method;

    public DrawMethodCommand(InterClass interClass, Method method) {
        this.interClass = interClass;
        this.method = method;
    }

    @Override
    public void doCommand() {
        if(interClass instanceof Klasa){
            ((Klasa) interClass).addMethod(method);
        }
        if(interClass instanceof Interface){
            ((Interface) interClass).addMethod(method);
        }
    }

    @Override
    public void undoCommand() {
        if(interClass instanceof Klasa){
            ((Klasa) interClass).removeMethod(method);
        }
        if(interClass instanceof Interface){
            ((Interface) interClass).removeMethod(method);

        }
    }
}
