package main.java.raf.dsw.classycraft.app.gui.swing.painter.cp;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class ConnectionPainter extends ElementPainter {
	private InterClassPainter from;
	private InterClassPainter to;
	
	@Override
	public boolean contains(Point p) {
		Point a = getFrom().getConnectionPoints().get(0);
		Point b = getTo().getConnectionPoints().get(0);
		for(Point p1 : getFrom().getConnectionPoints()) {
			for(Point p2 : getTo().getConnectionPoints()) {
				if(p1.distance(p2) < a.distance(b)) {
					a = p1;
					b = p2;
				}
			}
		}
		double distance = Line2D.ptSegDistSq(a.x, a.y, b.x, b.y, p.x, p.y);
		return distance < 2;
	}
	
	public ConnectionPainter(Connection diagramElement) {
		super(diagramElement);
		for(ElementPainter ed : ((DiagramView) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getElementPainters()) {
			if(ed instanceof InterClassPainter) {
				if(ed.getDiagramElement().equals(diagramElement.getFrom())) {
					from = (InterClassPainter) ed;
				}
				if(ed.getDiagramElement().equals(diagramElement.getTo())) {
					to = (InterClassPainter) ed;
				}
			}
		}
		
	}
	public boolean intersects(Rectangle r) {
		Point2D.Double a = new Point2D.Double(from.getX(), from.getY());
		Point2D.Double b = new Point2D.Double(to.getX(), to.getY());
		Point2D.Double unitVectorFromAtoB = new Point2D.Double(((b.x - a.x) / Math.sqrt((b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y))), ((b.y - a.y) / Math.sqrt((b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y))));
		double m = 0.2;
		double dist = a.distance(b);
		ArrayList<Point2D.Double> points = new ArrayList<>();
		while(m < dist) {
			points.add(new Point2D.Double(unitVectorFromAtoB.x * m, unitVectorFromAtoB.y * m));
			m += 0.2;
		}
		for(Point2D.Double p: points){
			if(r.contains(p)){
				return  true;
			}
		}
		return false;
	}
	
	public InterClassPainter getFrom() {
		return from;
	}
	
	public void setFrom(InterClassPainter from) {
		this.from = from;
	}
	
	public InterClassPainter getTo() {
		return to;
	}
	
	public void setTo(InterClassPainter to) {
		this.to = to;
	}
}
