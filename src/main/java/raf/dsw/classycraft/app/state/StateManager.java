package main.java.raf.dsw.classycraft.app.state;

import main.java.raf.dsw.classycraft.app.state.concrete.RemoveState;
import main.java.raf.dsw.classycraft.app.state.concrete.SelectState;
import main.java.raf.dsw.classycraft.app.state.concrete.ZoomInState;
import main.java.raf.dsw.classycraft.app.state.concrete.ZoomOutState;
import main.java.raf.dsw.classycraft.app.state.concrete.dc.*;
import main.java.raf.dsw.classycraft.app.state.concrete.dic.DrawClassState;
import main.java.raf.dsw.classycraft.app.state.concrete.dic.DrawEnumState;
import main.java.raf.dsw.classycraft.app.state.concrete.dic.DrawInterClassState;
import main.java.raf.dsw.classycraft.app.state.concrete.dic.DrawInterfaceState;

public class StateManager {
    private State currentState;
    private final SelectState selectState;
    private final RemoveState removeState;
    private final DrawClassState drawClassState;
    private final DrawInterfaceState drawInterfaceState;
    private final DrawEnumState drawEnumState;
    private final DrawGeneralisationState drawGeneralisationState;
    private final DrawDependencyState drawDependencyState;
    private final DrawCompositionState drawCompositionState;
    private final DrawAggregationState drawAggregationState;
    private final ZoomInState zoomInState;
    private final ZoomOutState zoomOutState;

    public StateManager() {
        selectState = new SelectState();
        removeState = new RemoveState();
        drawClassState = new DrawClassState();
        drawEnumState = new DrawEnumState();
        drawInterfaceState = new DrawInterfaceState();
        drawGeneralisationState = new DrawGeneralisationState();
        drawDependencyState = new DrawDependencyState();
        drawCompositionState = new DrawCompositionState();
        drawAggregationState = new DrawAggregationState();
        zoomInState = new ZoomInState();
        zoomOutState = new ZoomOutState();
        currentState = selectState;
    }


    public State getCurrentState() {
        return currentState;
    }

    public void setSelectState() {
        System.out.println("CURRENT STATE: SELECT");
        currentState = selectState;
    }

    public void setRemoveState() {
        System.out.println("CURRENT STATE: REMOVE");
        currentState = removeState;
    }

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

    public void setZoomInState() {
        System.out.println("CURRENT STATE: ZOOM IN");
        currentState = zoomInState;
    }

    public void setZoomOutState() {
        System.out.println("CURRENT STATE: ZOOM OUT");
        currentState = zoomOutState;
    }
}
