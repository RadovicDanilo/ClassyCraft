package main.java.raf.dsw.classycraft.app.state.concrete;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.state.State;
import main.java.raf.dsw.classycraft.app.state.StateImplement;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MultiSelectState extends StateImplement implements State {
	@Override
	public void mousePressed(MouseEvent e, DiagramView diagramView) {
		diagramView.setSelected(new ArrayList<>());

		diagramView.setSelectFrom(diagramView.correctMouse(e.getPoint()));
		diagramView.setSelectTo(diagramView.correctMouse(e.getPoint()));
	}
	
	@Override
	public void mouseDragged(MouseEvent e, DiagramView diagramView) {
		diagramView.setSelectTo(diagramView.correctMouse(e.getPoint()));
		
		Rectangle r = diagramView.getSelectionRectangle();
		
		diagramView.setSelected(new ArrayList<>());
		
		for(ElementPainter elementPainter : diagramView.getElementPainters()) {
			if(elementPainter.intersects(r)) {
				diagramView.addSelectedElement(elementPainter);
			}
		}
		
		
	}
	
	
	@Override
	public void mouseRelease(MouseEvent e, DiagramView diagramView) {
		diagramView.setSelectFrom(null);
		diagramView.setSelectFrom(null);
	}
}
