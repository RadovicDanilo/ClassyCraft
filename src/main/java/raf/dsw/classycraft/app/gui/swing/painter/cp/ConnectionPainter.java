package main.java.raf.dsw.classycraft.app.gui.swing.painter.cp;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;

import java.awt.*;
import java.awt.geom.Line2D;

public abstract class ConnectionPainter extends ElementPainter {
	
	public ConnectionPainter(Connection diagramElement) {
		super(diagramElement);
		
	}
	
	@Override
	public boolean contains(Point p) {
		Point a = getDiagramElement().getFrom().getConnectionPoints().get(0);
		Point b = getDiagramElement().getTo().getConnectionPoints().get(0);
		for(Point p1 : getDiagramElement().getFrom().getConnectionPoints()) {
			for(Point p2 : getDiagramElement().getTo().getConnectionPoints()) {
				if(p1.distance(p2) < a.distance(b)) {
					a = p1;
					b = p2;
				}
			}
		}
		double distance = Line2D.ptSegDistSq(a.x, a.y, b.x, b.y, p.x, p.y);
		return distance < 2;
	}
	
	@Override
	public Connection getDiagramElement() {
		return (Connection) super.getDiagramElement();
	}
	
	@Override
	public boolean contains(int x, int y) {
		return contains(new Point(x, y));
	}
	
	public boolean intersects(Rectangle r) {
		
		Point a = getDiagramElement().getFrom().getConnectionPoints().get(0);
		Point b = getDiagramElement().getTo().getConnectionPoints().get(0);
		for(Point p1 : getDiagramElement().getFrom().getConnectionPoints()) {
			for(Point p2 : getDiagramElement().getTo().getConnectionPoints()) {
				if(p1.distance(p2) < a.distance(b)) {
					a = p1;
					b = p2;
				}
			}
		}
		Point unitVectorBToA = new Point(a.x - b.x, a.y - b.y);
		double intensityOfUnitVector = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
		unitVectorBToA.setLocation((double) unitVectorBToA.x / intensityOfUnitVector, (double) unitVectorBToA.y / intensityOfUnitVector);
		for(int i = 0; i < (int) intensityOfUnitVector; i++) {
			if(r.contains(new Point(b.x + i * unitVectorBToA.x, b.y + i * unitVectorBToA.y)))
				return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof ConnectionPainter && ((ConnectionPainter) obj).getDiagramElement().getFrom().equals(this.getDiagramElement().getFrom())
			&& ((ConnectionPainter) obj).getDiagramElement().getTo().equals(this.getDiagramElement().getTo());
	}
}
