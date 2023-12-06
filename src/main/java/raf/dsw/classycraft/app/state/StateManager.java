package main.java.raf.dsw.classycraft.app.state;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.state.concrete.MultiSelectState;
import main.java.raf.dsw.classycraft.app.state.concrete.RemoveState;
import main.java.raf.dsw.classycraft.app.state.concrete.SelectState;
import main.java.raf.dsw.classycraft.app.state.concrete.ZoomToFitState;
import main.java.raf.dsw.classycraft.app.state.concrete.dc.*;
import main.java.raf.dsw.classycraft.app.state.concrete.dic.DrawClassState;
import main.java.raf.dsw.classycraft.app.state.concrete.dic.DrawEnumState;
import main.java.raf.dsw.classycraft.app.state.concrete.dic.DrawInterfaceState;
import main.java.raf.dsw.classycraft.app.state.concrete.dic.dcc.DrawFieldState;
import main.java.raf.dsw.classycraft.app.state.concrete.dic.dcc.DrawMethodState;
import main.java.raf.dsw.classycraft.app.state.concrete.dic.dcc.EditContentState;

public class StateManager {
	private State currentState;
	
	private final SelectState selectState;
	private final MultiSelectState multiSelectState;
	private final RemoveState removeState;
	
	private final DrawClassState drawClassState;
	private final DrawInterfaceState drawInterfaceState;
	private final DrawEnumState drawEnumState;
	
	private final DrawFieldState drawFieldState;
	private final DrawMethodState drawMethodState;
	
	private final DrawGeneralisationState drawGeneralisationState;
	private final DrawDependencyState drawDependencyState;
	private final DrawCompositionState drawCompositionState;
	private final DrawAggregationState drawAggregationState;
	private final EditContentState editContentState;
	private final ZoomToFitState zoomToFitState;
	
	
	public StateManager() {
		selectState = new SelectState();
		multiSelectState = new MultiSelectState();
		removeState = new RemoveState();
		drawClassState = new DrawClassState();
		drawEnumState = new DrawEnumState();
		drawInterfaceState = new DrawInterfaceState();
		drawFieldState = new DrawFieldState();
		drawMethodState = new DrawMethodState();
		drawGeneralisationState = new DrawGeneralisationState();
		drawDependencyState = new DrawDependencyState();
		drawCompositionState = new DrawCompositionState();
		drawAggregationState = new DrawAggregationState();
		zoomToFitState = new ZoomToFitState();
		editContentState = new EditContentState();
		currentState = selectState;
	}
	
	
	public State getCurrentState() {
		return currentState;
	}
	
	public void setSelectState() {
		System.out.println("CURRENT STATE: SELECT");
		currentState = selectState;
	}
	public void setMultiSelectState() {
		System.out.println("CURRENT STATE: MULTI SELECT");
		currentState = multiSelectState;
	}
	
	public void setRemoveState() {
		System.out.println("CURRENT STATE: REMOVE");
		
		if(MainFrame.getInstance().getPackageView() != null && MainFrame.getInstance().getPackageView().getTabbedPane() != null && ((DiagramView) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()) != null) {
			DiagramView dv = (DiagramView) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent();
			for(ElementPainter elementPainter: dv.getElementPainters()){
				if(dv.getSelected().contains(elementPainter)){
					dv.getElementPainters().remove(elementPainter);
					MainFrame.getInstance().getClassyTree().removeNode(new ClassyTreeItem(elementPainter.getDiagramElement()));
				}
			}
		}
		currentState = removeState;
	}
	//=================================================================
	
	public void setDrawClassState() {
		
		System.out.println("CURRENT STATE: DRAW CLASS");
		currentState = drawClassState;
	}
	
	public void setDrawInterfaceState() {
		System.out.println("CURRENT STATE: DRAW INTERFACE");
		
		currentState = drawInterfaceState;
	}
	
	public void setDrawEnumState() {
		System.out.println("CURRENT STATE: DRAW ENUM");
		currentState = drawEnumState;
	}
	
	//=================================================================
	public void setDrawFieldState() {
		System.out.println("CURRENT STATE: DRAW FIELD");
		currentState = drawFieldState;
	}
	
	public void setDrawMethodState() {
		System.out.println("CURRENT STATE: DRAW METHOD");
		currentState = drawMethodState;
	}
	
	public void setEditContentState() {
		System.out.println("CURRENT STATE: EDIT CONTENT");
		currentState = editContentState;
	}
	
	//=================================================================
	public void setDrawAggregationState() {
		System.out.println("CURRENT STATE: DRAW AGGREGATION");
		currentState = drawAggregationState;
	}
	
	public void setDrawGeneralisationState() {
		System.out.println("CURRENT STATE: DRAW GENERALISATION");
		currentState = drawGeneralisationState;
	}
	
	public void setDrawCompositionState() {
		System.out.println("CURRENT STATE: DRAW COMPOSITION");
		currentState = drawCompositionState;
	}
	
	public void setDrawDependencyState() {
		System.out.println("CURRENT STATE: DRAW DEPENDENCY");
		currentState = drawDependencyState;
	}
	
	//=================================================================
	public void setZoomToFitState() {//TODO jel ovo potrebno uopste? proveriti kasnije
		System.out.println("CURRENT STATE: ZoomToFit");
		currentState = zoomToFitState;
	}
}
