package main.java.raf.dsw.classycraft.app.state;

import main.java.raf.dsw.classycraft.app.state.concrete.RemoveState;
import main.java.raf.dsw.classycraft.app.state.concrete.SelectState;
import main.java.raf.dsw.classycraft.app.state.concrete.dc.*;
import main.java.raf.dsw.classycraft.app.state.concrete.dic.DrawClassState;
import main.java.raf.dsw.classycraft.app.state.concrete.dic.DrawEnumState;
import main.java.raf.dsw.classycraft.app.state.concrete.dic.DrawInterClassState;
import main.java.raf.dsw.classycraft.app.state.concrete.dic.DrawInterfaceState;

public class StateManager{
	private State currentState;
	private SelectState selectState;
	private RemoveState removeState;
	private DrawClassState drawClassState;
	private DrawInterfaceState drawInterfaceState;
	private DrawEnumState drawEnumState;
	private DrawGeneralisationState drawGeneralisationState;
	private DrawDependencyState drawDependencyState;
	private DrawCompositionState drawCompositionState;
	private DrawAggregationState drawAggregationState;
	
	public StateManager(){
		selectState = new SelectState();
		removeState = new RemoveState();
		drawClassState = new DrawClassState();
		drawEnumState = new DrawEnumState();
		drawInterfaceState = new DrawInterfaceState();
		drawGeneralisationState = new DrawGeneralisationState();
		drawDependencyState = new DrawDependencyState();
		drawCompositionState = new DrawCompositionState();
		drawAggregationState = new DrawAggregationState();
		currentState = selectState;
	}
	
	
	public State getCurrentState(){
		return currentState;
	}
	
	public void setRemoveState(){
		currentState = removeState;
	}
	
	public void setDrawClassState(){
		currentState = drawClassState;
	}
	
	public void setDrawInterfaceState(){
		currentState = drawInterfaceState;
	}
	
	public void setDrawEnumState(){
		currentState = drawEnumState;
	}
	
	public void setDrawAggregationState(){
		currentState = drawAggregationState;
	}
	
	public void setDrawGeneralisationState(){
		currentState = drawGeneralisationState;
	}
	
	public void setDrawCompositionState(){
		currentState = drawCompositionState;
	}
	
	public void setDrawDependencyState(){
		currentState = drawDependencyState;
	}
	
	public void setSelectState(){
		currentState = selectState;
	}
	
}
