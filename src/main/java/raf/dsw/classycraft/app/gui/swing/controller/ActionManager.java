package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import javax.swing.*;

public class ActionManager {
    private ExitAction exitAction;
    private NewProjectAction newProjectAction;
    private AboutUsAction aboutUsAction;
    private ChangeThemeAction changeThemeAction;

    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions() {
        exitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        aboutUsAction = new AboutUsAction();
        changeThemeAction = new ChangeThemeAction();
    }

    public Action getExitAction() {
        return exitAction;
    }
    public Action getNewProjectAction() {
        return newProjectAction;
    }
    public Action getAboutUsAction() {
        return aboutUsAction;
    }
    public Action getChangeThemeAction() {
        return changeThemeAction;
    }
}
