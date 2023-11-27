package main.java.raf.dsw.classycraft.app.gui.swing.view.view;

import main.java.raf.dsw.classycraft.app.model.observer.ISubscriber;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;

import javax.swing.*;

public class DiagramView extends JPanel implements ISubscriber{
	private Diagram diagram;
	
	public DiagramView(Diagram diagram){
		this.diagram = diagram;
		diagram.addSubscriber(this);
	}
	
	public Diagram getDiagram(){
		return diagram;
	}
	
	public void setDiagram(Diagram diagram){
		this.diagram = diagram;
	}
	
	@Override
	public void update(Object notification){
	
	}
}
