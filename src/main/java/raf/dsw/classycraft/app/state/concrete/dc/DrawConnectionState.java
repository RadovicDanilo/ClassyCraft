package main.java.raf.dsw.classycraft.app.state.concrete.dc;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.state.State;
import main.java.raf.dsw.classycraft.app.state.StateImplement;

import java.awt.event.MouseEvent;

public abstract class DrawConnectionState extends StateImplement implements State {
	private InterClassPainter from;
	
	@Override
	public void mousePressed(MouseEvent e, DiagramView diagramView) {
		for(ElementPainter ep : diagramView.getElementPainters()) {
			if(ep instanceof InterClassPainter && ((InterClassPainter) ep).getRectangle().contains(diagramView.adjustPoint(e.getPoint()))) {
				from = (InterClassPainter) ep;
				diagramView.setConnectionFrom(((InterClassPainter) ep).getDiagramElement().getConnectionPoints().get(0));
				diagramView.setConnectionTo(diagramView.adjustPoint(e.getPoint()));
				break;
			}
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e, DiagramView diagramView) {
		if(diagramView.getConnectionFrom() == null)
			return;
		diagramView.setConnectionFrom(from.getDiagramElement().closestConnectionPoint(diagramView.adjustPoint(e.getPoint())));
		diagramView.setConnectionTo(diagramView.adjustPoint(e.getPoint()));
	}
	
	public InterClassPainter getFrom() {
		return from;
	}
}
