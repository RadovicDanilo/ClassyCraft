package main.java.raf.dsw.classycraft.app.gui.swing.view.view;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DiagramView extends JPanel implements ISubscriber {
	public final BasicStroke strokeDashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f);
	private Diagram diagram;
	private ArrayList<ElementPainter> elementPainters;
	private Point connectionFrom;
	private Point connectionTo;
	private Point selectFrom;
	private Point selectTo;
	private ArrayList<ElementPainter> selected;
	
	public DiagramView(Diagram diagram) {
		this.diagram = diagram;
		diagram.addSubscriber(this);
		elementPainters = new ArrayList<>();
		this.addMouseListener(new MyMouseAdapter(this));
		this.addMouseMotionListener(new MyMouseAdapter(this));
		connectionFrom = null;
		connectionTo = null;
		selectFrom = null;
		selectTo = null;
		selected = new ArrayList<>();
	}
	
	
	public Rectangle getSelectionRectangle() {
		Rectangle r = new Rectangle();
		
		if(selectFrom != null && selectTo != null) {
			if(selectFrom.x < selectTo.x && selectFrom.y < selectTo.y) {
				r.setLocation(selectFrom.x, selectFrom.y);
				r.setSize(selectTo.x - selectFrom.x, selectTo.y - selectFrom.y);
				
			}else if(selectFrom.x > selectTo.x && selectFrom.y > selectTo.y) {
				r.setLocation(selectTo.x, selectTo.y);
				r.setSize(selectFrom.x - selectTo.x, selectFrom.y - selectTo.y);
				
			}else if(selectFrom.x > selectTo.x && selectFrom.y < selectTo.y) {
				r.setLocation(selectTo.x, selectFrom.y);
				r.setSize(selectFrom.x - selectTo.x, selectTo.y - selectFrom.y);
				
			}else {
				r.setLocation(selectFrom.x, selectTo.y);
				r.setSize(-selectFrom.x + selectTo.x, -selectTo.y + selectFrom.y);
			}
		}
		return r;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		if(connectionFrom != null && connectionTo != null) {
			g2d.setStroke(strokeDashed);
			g2d.setColor(Color.RED);
			g2d.drawLine(connectionFrom.x, connectionFrom.y, connectionTo.x, connectionTo.y);
		}
		
		if(selectFrom != null && selectTo != null) {
			g2d.setStroke(strokeDashed);
			g2d.setColor(Color.RED);
			if(selectFrom.x < selectTo.x && selectFrom.y < selectTo.y) {
				g.drawRect(selectFrom.x, selectFrom.y, selectTo.x - selectFrom.x, selectTo.y - selectFrom.y);
			}else if(selectFrom.x > selectTo.x && selectFrom.y > selectTo.y) {
				g.drawRect(selectTo.x, selectTo.y, selectFrom.x - selectTo.x, selectFrom.y - selectTo.y);
			}else if(selectFrom.x > selectTo.x && selectFrom.y < selectTo.y) {
				g.drawRect(selectTo.x, selectFrom.y, selectFrom.x - selectTo.x, selectTo.y - selectFrom.y);
			}else {
				g.drawRect(selectFrom.x, selectTo.y, -selectFrom.x + selectTo.x, -selectTo.y + selectFrom.y);
			}
		}
		for(ElementPainter elementPainter : elementPainters) {
			elementPainter.draw((Graphics2D) g);
		}
	}
	
	@Override
	public void update(Object notification) {
		repaint();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DiagramView) {
			return this.diagram.equals(((DiagramView) obj).getDiagram());
		}
		return false;
	}
	
	public Diagram getDiagram() {
		return diagram;
	}
	
	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}
	
	public void addSelectedElement(ElementPainter elementPainter) {
		if(!selected.contains(elementPainter)) {
			selected.add(elementPainter);
		}
		repaint();
	}
	
	public ArrayList<ElementPainter> getSelected() {
		return selected;
	}
	
	public void setSelected(ArrayList<ElementPainter> selected) {
		this.selected = selected;
		repaint();
	}
	
	public ArrayList<ElementPainter> getElementPainters() {
		return elementPainters;
	}
	
	public void setElementPainters(ArrayList<ElementPainter> elementPainters) {
		this.elementPainters = elementPainters;
	}
	
	public Point getSelectFrom() {
		return selectFrom;
	}
	
	public void setSelectFrom(Point selectFrom) {
		repaint();
		this.selectFrom = selectFrom;
	}
	
	public Point getSelectTo() {
		return selectTo;
	}
	
	public void setSelectTo(Point selectTo) {
		repaint();
		this.selectTo = selectTo;
	}
	
	public Point getConnectionFrom() {
		return connectionFrom;
		
	}
	
	public void setConnectionFrom(Point connectionFrom) {
		repaint();
		this.connectionFrom = connectionFrom;
	}
	
	public Point getConnectionTo() {
		return connectionTo;
	}
	
	public void setConnectionTo(Point connectionTo) {
		repaint();
		this.connectionTo = connectionTo;
	}
	
}
