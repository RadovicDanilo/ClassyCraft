package main.java.raf.dsw.classycraft.app.state.concrete;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SelectState implements State {
	private Point last = null;
	
	@Override
	public void mousePressed(MouseEvent e, DiagramView diagramView) {
		for(ElementPainter elementPainter : diagramView.getElementPainters()) {
			if(elementPainter.contains(e.getPoint())) {
				last = e.getPoint();
				diagramView.setSelected(new ArrayList<>());
				diagramView.addSelectedElement(elementPainter);
				return;
			}
		}
		diagramView.setSelected(new ArrayList<>());
	}
	
	@Override
	public void mouseDragged(MouseEvent e, DiagramView diagramView) {
		if(last != null){
			diagramView.moveSelectedBy(new Point(e.getX() - last.x, e.getY() - last.y));
			last = e.getPoint();
		}
		
	}
	
	@Override
	public void mouseRelease(MouseEvent e, DiagramView diagramView) {
		last = null;
	}
}
