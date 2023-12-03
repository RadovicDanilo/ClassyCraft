package main.java.raf.dsw.classycraft.app.state.concrete.dic;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.ClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.EnumPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.ElementFactory;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.enumeration.InterClassType;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;

import java.awt.event.MouseEvent;

public class DrawClassState extends DrawInterClassState {
	
	@Override
	public void mouseClicked(MouseEvent e, DiagramView diagramView) {
		ElementFactory elementFactory = new ElementFactory();
		
		Klasa klasa = (Klasa) elementFactory.createInterClass(InterClassType.CLASS, diagramView.getDiagram(), Visibility.PUBLIC);
		
		ClassPainter classPainter = new ClassPainter(klasa, e.getX(), e.getY());
		
		
		classPainter.addElement(klasa);
		diagramView.getElementPainters().add(classPainter);
	}
	
	@Override
	public void mouseDragged(MouseEvent e, DiagramView diagramView) {
	
	}
	
	@Override
	public void mouseRelease(MouseEvent e, DiagramView diagramView) {
	
	}
}
