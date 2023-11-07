package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import javax.swing.*;

public class ActionManager {
    private ChangeThemeAction changeThemeAction;
    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private NewProjectAction newProjectAction;
    private NewPackageAction newPackageAction;
    private NewDiagramAction newDiagramAction;
    private DeleteNodeAction deleteNodeAction;

    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions() {
        exitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        aboutUsAction = new AboutUsAction();
        changeThemeAction = new ChangeThemeAction();
        deleteNodeAction = new DeleteNodeAction();
        newPackageAction = new NewPackageAction();
        newDiagramAction = new NewDiagramAction();
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
    public DeleteNodeAction getDeleteNodeAction() {
        return deleteNodeAction;
    }
    public NewPackageAction getNewPackageAction() {
        return newPackageAction;
    }
    public NewDiagramAction getNewDiagramAction() {
        return newDiagramAction;
    }
}
