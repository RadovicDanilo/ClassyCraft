package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import javax.swing.*;

public class ActionManager {
    private ExitAction exitAction;
    private NewProjectAction newProjectAction;
    private AboutUsAction aboutUsAction;

    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions() {
        exitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        aboutUsAction = new AboutUsAction();
    }

    public Action getExitAction() {
        return exitAction;
    }
    public Action getNewProjectAction() {
        return newProjectAction;
    }

    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }
}
