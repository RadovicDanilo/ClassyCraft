package main.java.raf.dsw.classycraft.app.gui.swing.painter;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;

import java.awt.*;

public abstract class ElementPainter {
	public final BasicStroke strokeDashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f);
	public final BasicStroke normalStroke = new BasicStroke(1.0f);
	private final DiagramElement diagramElement;
	public abstract boolean contains(Point p);
	public ElementPainter(DiagramElement diagramElement) {
		this.diagramElement = diagramElement;
	}
	
	public void addElement(DiagramElement element) {
		MainFrame.getInstance().getClassyTree().addChild(MainFrame.getInstance().getClassyTree().getNode(element.getParent()),element);
	}
	
	public void draw(Graphics2D g) {
	
	}
	
	public DiagramElement getDiagramElement() {
		return diagramElement;
	}
	
	
	public abstract boolean intersects(Rectangle r);
	
}
