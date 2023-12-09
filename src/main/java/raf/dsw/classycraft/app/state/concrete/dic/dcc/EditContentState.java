package main.java.raf.dsw.classycraft.app.state.concrete.dic.dcc;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.EditContentPane;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EditContentState implements State {
	@Override
	public void mousePressed(MouseEvent e, DiagramView diagramView) {
		for(ElementPainter elementPainter : diagramView.getElementPainters()) {
			if(!(elementPainter instanceof InterClassPainter))
				continue;
			if(((InterClassPainter) elementPainter).getRectangle().contains(new Point(diagramView.correctMouseX(e.getX()), diagramView.correctMouseY(e.getY())))) {
				EditContentPane editContentPane = new EditContentPane(elementPainter);
				editContentPane.setVisible(true);
			}
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e, DiagramView diagramView) {
	
	}
	
	@Override
	public void mouseRelease(MouseEvent e, DiagramView diagramView) {
	
	}
}
