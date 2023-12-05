package main.java.raf.dsw.classycraft.app.gui.swing.painter.cp;

import main.java.raf.dsw.classycraft.app.Main;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.rmi.MarshalException;

public class AggregationPainter extends ConnectionPainter {
	
	public AggregationPainter(Connection diagramElement) {
		super(diagramElement);
	}
	
	@Override
	public void addElement(DiagramElement element) {
		super.addElement(element);
	}
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		if(((DiagramView) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getSelected().contains(this)){
			g.setColor(Color.RED);
			g.setStroke(strokeDashed);
		}else{
			g.setColor(Color.BLACK);
			g.setStroke(normalStroke);
		}
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
		g.drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());
		//TODO DIAMOND
		
	}
	
	
}
