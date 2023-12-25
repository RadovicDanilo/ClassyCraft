package main.java.raf.dsw.classycraft.app.state;

import main.java.raf.dsw.classycraft.app.command.implementation.DeleteElementCommand;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.connection_painter.ConnectionPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.state.concrete.*;
import main.java.raf.dsw.classycraft.app.state.concrete.draw_class.DrawAggregationState;
import main.java.raf.dsw.classycraft.app.state.concrete.draw_class.DrawCompositionState;
import main.java.raf.dsw.classycraft.app.state.concrete.draw_class.DrawDependencyState;
import main.java.raf.dsw.classycraft.app.state.concrete.draw_class.DrawGeneralisationState;
import main.java.raf.dsw.classycraft.app.state.concrete.draw_interclass.DrawClassState;
import main.java.raf.dsw.classycraft.app.state.concrete.draw_interclass.DrawEnumState;
import main.java.raf.dsw.classycraft.app.state.concrete.draw_interclass.DrawInterfaceState;
import main.java.raf.dsw.classycraft.app.state.concrete.draw_interclass.dcc.DrawFieldState;
import main.java.raf.dsw.classycraft.app.state.concrete.draw_interclass.dcc.DrawMethodState;
import main.java.raf.dsw.classycraft.app.state.concrete.draw_interclass.dcc.EditContentState;

import java.util.ArrayList;

public class StateManager {
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
    private final DuplicateState duplicateState;
    private State currentState;


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
        duplicateState = new DuplicateState();
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
        currentState = removeState;
        if (MainFrame.getInstance().getPackageView() == null || MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent() == null || ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView() == null) {
            return;
        }
        DiagramView dv = ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView();
        ArrayList<ElementPainter> removedElements = dv.getSelected();
        if (removedElements.size() == 0)
            return;
        for (ElementPainter removedElement : removedElements) {
            for (int i = 0; i < dv.getElementPainters().size(); i++) {
                ElementPainter elementPainter = dv.getElementPainters().get(i);
                if (elementPainter instanceof ConnectionPainter && (((ConnectionPainter) elementPainter).getDiagramElement().getFrom() == removedElement.getDiagramElement() || ((ConnectionPainter) elementPainter).getDiagramElement().getTo() == removedElement.getDiagramElement())) {
                    removedElements.add(elementPainter);
                }
            }
        }
        dv.setSelected(new ArrayList<>());
        dv.getCommandManager().addCommand(new DeleteElementCommand(removedElements));
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
    public void setZoomToFitState() {
        System.out.println("CURRENT STATE: ZOOM TO FIT");
        if (MainFrame.getInstance().getPackageView() == null || MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent() == null || ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView() == null) {
            return;
        }
        DiagramView dv = ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView();
        dv.zoomToFit();
        currentState = zoomToFitState;
    }

    public void setDuplicateState() {
        System.out.println("CURRENT STATE: DUPLICATE");
        currentState = duplicateState;
        if (MainFrame.getInstance().getPackageView() == null || MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent() == null || ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView() == null) {
            return;
        }
        DiagramView dv = ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView();
        if (dv.getSelected().size() == 1) {
            duplicateState.duplicate(dv.getSelected().get(0), dv);
        }
    }
}
