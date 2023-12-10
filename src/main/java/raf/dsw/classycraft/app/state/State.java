package main.java.raf.dsw.classycraft.app.state;

import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface State {
	void mousePressed(MouseEvent e, DiagramView diagramView);
	
	void mouseDragged(MouseEvent e, DiagramView diagramView);
	
	void mouseRelease(MouseEvent e, DiagramView diagramView);
	void mouseWheelMoved(MouseWheelEvent e, DiagramView diagramView);
}
