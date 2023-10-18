package main.java.raf.dsw.classycraft.app.gui.swing.controller;

public class ActionManager {
    private ExitAction exitAction;
    private NewProjectAction newProjectAction;

    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions() {
        exitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
    }
}
