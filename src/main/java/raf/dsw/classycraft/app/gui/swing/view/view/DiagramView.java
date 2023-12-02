package main.java.raf.dsw.classycraft.app.gui.swing.view.view;

import main.java.raf.dsw.classycraft.app.gui.swing.view.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.model.observer.ISubscriber;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class DiagramView extends JPanel implements ISubscriber {
	private Diagram diagram;
	ArrayList<ElementPainter> elementPainters;
	
	public DiagramView(Diagram diagram) {
		this.diagram = diagram;
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
		repaint();
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
