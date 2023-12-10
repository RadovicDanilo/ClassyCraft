package main.java.raf.dsw.classycraft.app.state.concrete.dc;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.state.State;
import main.java.raf.dsw.classycraft.app.state.StateImplement;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class DrawConnectionState extends StateImplement implements State {
	private InterClassPainter from;
	
	@Override
	public void mousePressed(MouseEvent e, DiagramView diagramView) {
		for(ElementPainter ep : diagramView.getElementPainters()) {
			if(ep instanceof InterClassPainter && ((InterClassPainter) ep).getRectangle().contains(new Point(diagramView.correctMouseX(e.getX()), diagramView.correctMouseY(e.getY())))) {
				from = (InterClassPainter) ep;
				diagramView.setConnectionFrom(((InterClassPainter) ep).getDiagramElement().getConnectionPoints().get(0));
				diagramView.setConnectionTo(new Point(diagramView.correctMouseX(e.getX()), diagramView.correctMouseY(e.getY())));
				break;
			}
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e, DiagramView diagramView) {
		if(diagramView.getConnectionFrom() == null)
			return;
		diagramView.setConnectionFrom(from.getDiagramElement().closestConnectionPoint(new Point(diagramView.correctMouseX(e.getX()), diagramView.correctMouseY(e.getY()))));
		diagramView.setConnectionTo(new Point(diagramView.correctMouseX(e.getX()), diagramView.correctMouseY(e.getY())));
	}
	
	public InterClassPainter getFrom() {
		return from;
	}
}
