package main.java.raf.dsw.classycraft.app.controller;

import main.java.raf.dsw.classycraft.app.controller.menu_and_toolbar.*;
import main.java.raf.dsw.classycraft.app.controller.sidebar.*;
import main.java.raf.dsw.classycraft.app.controller.sidebar.draw_connection.DrawAggregationAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.draw_connection.DrawCompositionAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.draw_connection.DrawDependencyAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.draw_connection.DrawGeneralisationAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.draw_interclass.DrawClassAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.draw_interclass.DrawEnumAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.draw_interclass.DrawInterfaceAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.draw_interclass.draw_class_content.DrawFieldAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.draw_interclass.draw_class_content.DrawMethodAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.draw_interclass.draw_class_content.EditContentAction;

import javax.swing.*;

public class ActionManager {
    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private NewProjectAction newProjectAction;
    private NewPackageAction newPackageAction;
    private NewDiagramAction newDiagramAction;
    private DeleteNodeAction deleteNodeAction;
    private ChangeAuthorAction changeAuthorShowViewAction;
    private SelectAction selectAction;
    private RemoveAction removeAction;
    private DrawClassAction drawClassAction;
    private DrawEnumAction drawEnumAction;
    private DrawInterfaceAction drawInterfaceAction;
    private DrawGeneralisationAction drawGeneralisationAction;
    private DrawCompositionAction drawCompositionAction;
    private DrawAggregationAction drawAggregationAction;
    private DrawDependencyAction drawDependencyAction;
    private ZoomToFitAction zoomToFitAction;
    private DrawFieldAction drawFieldAction;
    private DrawMethodAction drawMethodAction;
    private EditContentAction editContentAction;
    private MultiSelectStateAction multiSelectStateAction;
    private DuplicateAction duplicateAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private ExportProjectAsJavaCodeAction exportProjectAsJavaCodeAction;

    private SaveAsAction saveAsAction;
    private SaveAction saveAction;
    private OpenAction openAction;

    private SaveDiagramAsTemplateAction saveDiagramAsTemplateAction;
    private SaveDiagramAsScreenshotAction saveDiagramAsScreenshotAction;


    public ActionManager() {
        initialiseActions();
    }

    private void initialiseActions() {
        exitAction = new ExitAction();
        newProjectAction = new NewProjectAction();
        aboutUsAction = new AboutUsAction();
        deleteNodeAction = new DeleteNodeAction();
        newPackageAction = new NewPackageAction();
        newDiagramAction = new NewDiagramAction();
        changeAuthorShowViewAction = new ChangeAuthorAction();
        multiSelectStateAction = new MultiSelectStateAction();
        selectAction = new SelectAction();
        removeAction = new RemoveAction();
        duplicateAction = new DuplicateAction();

        drawClassAction = new DrawClassAction();
        drawEnumAction = new DrawEnumAction();
        drawInterfaceAction = new DrawInterfaceAction();

        drawFieldAction = new DrawFieldAction();
        drawMethodAction = new DrawMethodAction();
        editContentAction = new EditContentAction();

        drawAggregationAction = new DrawAggregationAction();
        drawCompositionAction = new DrawCompositionAction();
        drawGeneralisationAction = new DrawGeneralisationAction();
        drawDependencyAction = new DrawDependencyAction();

        zoomToFitAction = new ZoomToFitAction();

        redoAction = new RedoAction();
        undoAction = new UndoAction();

        exportProjectAsJavaCodeAction = new ExportProjectAsJavaCodeAction();

        saveAsAction = new SaveAsAction();
        saveAction = new SaveAction();
        openAction = new OpenAction();

        saveDiagramAsTemplateAction = new SaveDiagramAsTemplateAction();
        saveDiagramAsScreenshotAction = new SaveDiagramAsScreenshotAction();
    }

    public MultiSelectStateAction getMultiSelectStateAction() {
        return multiSelectStateAction;
    }

    public ExportProjectAsJavaCodeAction getExportProjectAsJavaCodeAction() {
        return exportProjectAsJavaCodeAction;
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

    public DeleteNodeAction getDeleteNodeAction() {
        return deleteNodeAction;
    }

    public NewPackageAction getNewPackageAction() {
        return newPackageAction;
    }

    public NewDiagramAction getNewDiagramAction() {
        return newDiagramAction;
    }

    public ChangeAuthorAction getChangeAuthorShowViewAction() {
        return changeAuthorShowViewAction;
    }

    public SelectAction getSelectAction() {
        return selectAction;
    }

    public RemoveAction getRemoveAction() {
        return removeAction;
    }

    public DrawClassAction getDrawClassAction() {
        return drawClassAction;
    }

    public DrawEnumAction getDrawEnumAction() {
        return drawEnumAction;
    }

    public DrawInterfaceAction getDrawInterfaceAction() {
        return drawInterfaceAction;
    }

    public DrawGeneralisationAction getDrawGeneralisationAction() {
        return drawGeneralisationAction;
    }

    public DrawCompositionAction getDrawCompositionAction() {
        return drawCompositionAction;
    }

    public DrawAggregationAction getDrawAggregationAction() {
        return drawAggregationAction;
    }

    public DrawDependencyAction getDrawDependencyAction() {
        return drawDependencyAction;
    }

    public ZoomToFitAction getZoomToFitAction() {
        return zoomToFitAction;
    }

    public DrawFieldAction getDrawFieldAction() {
        return drawFieldAction;
    }

    public DrawMethodAction getDrawMethodAction() {
        return drawMethodAction;
    }


    public EditContentAction getEditContentAction() {
        return editContentAction;
    }

    public Action getDuplicateAction() {
        return duplicateAction;
    }

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public RedoAction getRedoAction() {
        return redoAction;
    }

    public SaveAsAction getSaveAsAction() {
        return saveAsAction;
    }

    public SaveAction getSaveAction() {
        return saveAction;
    }

    public OpenAction getOpenAction() {
        return openAction;
    }

    public SaveDiagramAsTemplateAction getSaveDiagramAsTemplateAction() {
        return saveDiagramAsTemplateAction;
    }

    public SaveDiagramAsScreenshotAction getSaveDiagramAsScreenshotAction() {
        return saveDiagramAsScreenshotAction;
    }
}

