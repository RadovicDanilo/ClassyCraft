package main.java.raf.dsw.classycraft.app.gui.swing.view.view;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.cp.ConnectionPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.observer.ISubscriber;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;

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
		diagram.addSubscriber(this);//TODO PITANJE
		elementPainters = new ArrayList<>();
		this.addMouseListener(new MyMouseAdapter(this));
		this.addMouseMotionListener(new MyMouseAdapter(this));
		connectionFrom = null;
		connectionTo = null;
		selectFrom = null;
		selectTo = null;
		selected = new ArrayList<>();
	}
	
	public Point getSelectFrom() {
		return selectFrom;
	}
	
	public Point getSelectTo() {
		return selectTo;
	}
	
	public ArrayList<ElementPainter> getSelected() {
		return selected;
	}
	
	public void setSelected(ArrayList<ElementPainter> selected) {
		this.selected = selected;
		repaint();
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
			//TODO draw this
			//g2d.drawRect(selectFrom.x, selectFrom.y, selectTo.x, selectTo.y);
		}

		for(ElementPainter elementPainter : elementPainters) {
			//TODO if intersect /w rectangle -> sleceted.add(this)
			elementPainter.draw((Graphics2D) g);
		}
	}
	
	@Override
	public void update(Object notification) {
		if(notification instanceof Connection) {
			for(ElementPainter e : elementPainters) {
				if(e.getDiagramElement() == notification) {
					elementPainters.remove(e);
					break;
				}
			}
		}
		if(notification instanceof InterClass) {
			for(ElementPainter e : elementPainters) {
				if(e.getDiagramElement() == notification) {
					elementPainters.remove(e);
					break;
				}
			}
			for(int i = 0; i < elementPainters.size(); i++) {
				if(elementPainters.get(i) instanceof ConnectionPainter) {
					if(((ConnectionPainter) elementPainters.get(i)).getFrom().getDiagramElement() == notification || ((ConnectionPainter) elementPainters.get(i)).getTo().getDiagramElement() == notification) {
						MainFrame.getInstance().getClassyTree().removeNode(new ClassyTreeItem(elementPainters.get(i).getDiagramElement()));
						elementPainters.remove(elementPainters.get(i));
						i--;
					}
				}
			}
		}
		repaint();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DiagramView) {
			return this.diagram.equals(((DiagramView) obj).getDiagram());
		}
		return false;
	}
	
	public ArrayList<ElementPainter> getElementPainters() {
		return elementPainters;
	}
	
	public void setElementPainters(ArrayList<ElementPainter> elementPainters) {
		this.elementPainters = elementPainters;
	}
	
	public Point getConnectionFrom() {
		return connectionFrom;
		
	}
	
	public Point getConnectionTo() {
		return connectionTo;
	}
	
	public void setConnectionFrom(Point connectionFrom) {
		repaint();
		this.connectionFrom = connectionFrom;
	}
	
	public void setConnectionTo(Point connectionTo) {
		repaint();
		this.connectionTo = connectionTo;
	}
	
	public void setSelectFrom(Point selectFrom) {
		repaint();
		this.selectFrom = selectFrom;
	}
	
	public void setSelectTo(Point selectTo) {
		repaint();
		this.selectTo = selectTo;
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
	
	public void moveSelectedBy(Point point) {
		for(ElementPainter e : selected) {
			if(e instanceof InterClassPainter) {
				((InterClassPainter) e).setX(((InterClassPainter) e).getX() + point.x);
				((InterClassPainter) e).setY(((InterClassPainter) e).getY() + point.y);
			}
		}
		repaint();
	}
	public void removeSelected(){
		for(int i = 0; i < elementPainters.size(); i++) {
			if(selected.contains(elementPainters.get(i)) ) {
				MainFrame.getInstance().getClassyTree().removeNode(new ClassyTreeItem(elementPainters.get(i).getDiagramElement()));
				update(elementPainters.get(i).getDiagramElement());
				i--;
			}
		}
		repaint();
	}
	public void removePainter(ElementPainter elementPainter) {
		this.diagram.removeChild(elementPainter.getDiagramElement());
		MainFrame.getInstance().getClassyTree().removeNode(new ClassyTreeItem(elementPainter.getDiagramElement()));
		elementPainters.remove(elementPainter);
		
		for(int i = 0; i < elementPainters.size(); i++) {
			if(elementPainters.get(i) instanceof ConnectionPainter) {
				if(((ConnectionPainter) elementPainters.get(i)).getFrom() == elementPainter || ((ConnectionPainter) elementPainters.get(i)).getTo() == elementPainter) {
					MainFrame.getInstance().getClassyTree().removeNode(new ClassyTreeItem(elementPainters.get(i).getDiagramElement()));
					elementPainters.remove(elementPainters.get(i));
					i--;
				}
			}
		}
		repaint();
	}
}
