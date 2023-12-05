package main.java.raf.dsw.classycraft.app.controller;

import main.java.raf.dsw.classycraft.app.controller.menu_and_toolbar.*;
import main.java.raf.dsw.classycraft.app.controller.sidebar.RemoveAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.SelectAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.ZoomToFitAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.dc.DrawAggregationAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.dc.DrawCompositionAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.dc.DrawDependencyAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.dc.DrawGeneralisationAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.dic.DrawClassAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.dic.DrawEnumAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.dic.DrawInterfaceAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.dic.dcc.DrawFieldAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.dic.dcc.DrawMethodAction;
import main.java.raf.dsw.classycraft.app.controller.sidebar.dic.dcc.EditContentAction;
import main.java.raf.dsw.classycraft.app.controller.tree.*;

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
		
		selectAction = new SelectAction();
		removeAction = new RemoveAction();
		
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
}

