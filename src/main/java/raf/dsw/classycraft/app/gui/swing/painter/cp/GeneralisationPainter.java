package main.java.raf.dsw.classycraft.app.gui.swing.painter.cp;

import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;

import java.awt.*;

public class GeneralisationPainter extends ConnectionPainter {
	
	public GeneralisationPainter(Connection diagramElement) {
		super(diagramElement);
	}
	
	@Override
	public void addElement(DiagramElement element) {
		super.addElement(element);
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		if(((DiagramView) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getSelected().contains(this)) {
			g.setColor(Color.RED);
			g.setStroke(strokeDashed);
		}else {
			g.setColor(Color.BLACK);
			g.setStroke(normalStroke);
		}
		Point a = getFrom().getDiagramElement().getConnectionPoints().get(0);
		Point b = getTo().getDiagramElement().getConnectionPoints().get(0);
		for(Point p1 : getFrom().getDiagramElement().getConnectionPoints()) {
			for(Point p2 : getTo().getDiagramElement().getConnectionPoints()) {
				if(p1.distance(p2) < a.distance(b)) {
					a = p1;
					b = p2;
				}
			}
		}
		g.drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());
		
	}
}
