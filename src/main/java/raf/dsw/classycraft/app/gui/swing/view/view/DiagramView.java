package main.java.raf.dsw.classycraft.app.gui.swing.view.view;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.observer.ISubscriber;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DiagramView extends JPanel implements ISubscriber {
	private Diagram diagram;
	ArrayList<ElementPainter> elementPainters;
	
	public DiagramView(Diagram diagram) {
		this.diagram = diagram;
		diagram.addSubscriber(this);//TODO PITANJE
		elementPainters = new ArrayList<>();
		this.addMouseListener(new MyMouseAdapter(this));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(ElementPainter elementPainter : elementPainters) {
			elementPainter.draw((Graphics2D) g);
		}
	}
	
	@Override
	public void update(Object notification) {
		if(notification instanceof DiagramElement){
			for(ElementPainter e: elementPainters){
				if(e.getDiagramElement() == notification){
					elementPainters.remove(e);
					break;
				}
			}
		}
		repaint();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DiagramView){
			return this.diagram.equals(((DiagramView) obj).getDiagram());
		}
		return false;
	}
	
	public ArrayList<ElementPainter> getElementPainters() {
		return elementPainters;
	}
	
	public Diagram getDiagram() {
		return diagram;
	}
	
	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}
}
