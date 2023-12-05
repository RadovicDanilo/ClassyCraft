package main.java.raf.dsw.classycraft.app.state.concrete.dc;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.state.State;

import java.awt.event.MouseEvent;

public abstract class DrawConnectionState implements State {
	private InterClassPainter from;
	@Override
	public void mousePressed(MouseEvent e, DiagramView diagramView) {
		for(ElementPainter ep : diagramView.getElementPainters()) {
			if(ep instanceof InterClassPainter && ((InterClassPainter) ep).getRectangle().contains(e.getPoint())) {
				from = (InterClassPainter) ep;
				diagramView.setConnectionFrom(((InterClassPainter) ep).getConnectionPoints().get(0));
				diagramView.setConnectionTo(e.getPoint());
				break;
			}
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e, DiagramView diagramView) {
		
		diagramView.setConnectionFrom(from.closestConnectionPoint(e.getPoint()));
		diagramView.setConnectionTo(e.getPoint());
	}
	
	public InterClassPainter getFrom() {
		return from;
	}
}
