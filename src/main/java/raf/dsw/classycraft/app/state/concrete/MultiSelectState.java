package main.java.raf.dsw.classycraft.app.state.concrete;

import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.state.State;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MultiSelectState implements State {
	@Override
	public void mousePressed(MouseEvent e, DiagramView diagramView) {
		diagramView.setSelected(new ArrayList<>());
		diagramView.setSelectFrom(e.getPoint());
		diagramView.setSelectTo(e.getPoint());
	}
	
	@Override
	public void mouseDragged(MouseEvent e, DiagramView diagramView) {
		diagramView.setSelectTo(e.getPoint());
	}
	
	@Override
	public void mouseRelease(MouseEvent e, DiagramView diagramView) {
		diagramView.setSelectFrom(null);
		diagramView.setSelectFrom(null);
	}
}
