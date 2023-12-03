package main.java.raf.dsw.classycraft.app.state.concrete.dic;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.ClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterfacePainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.ElementFactory;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.enumeration.InterClassType;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;

import java.awt.event.MouseEvent;

public class DrawInterfaceState extends DrawInterClassState {
	@Override
	public void mouseClicked(MouseEvent e, DiagramView diagramView) {
		ElementFactory elementFactory = new ElementFactory();
		
		Interface anInterface = (Interface) elementFactory.createInterClass(InterClassType.INTERFACE, diagramView.getDiagram(), Visibility.PUBLIC);
		
		InterfacePainter interfacePainter = new InterfacePainter(anInterface, e.getX(), e.getY());
		
		
		interfacePainter.addElement(anInterface);
		diagramView.getElementPainters().add(interfacePainter);
	}
	
	@Override
	public void mouseDragged(MouseEvent e, DiagramView diagramView) {
	
	}
	
	@Override
	public void mouseRelease(MouseEvent e, DiagramView diagramView) {
	
	}
}
