package main.java.raf.dsw.classycraft.app.state.concrete.dic;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.EnumPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.ElementFactory;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.enumeration.InterClassType;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;

public class DrawEnumState extends DrawInterClassState {
	@Override
	public void mouseClicked(MouseEvent e, DiagramView diagramView) {
		ElementFactory elementFactory = new ElementFactory();
		
		Enum enumeracija = (Enum) elementFactory.createInterClass(InterClassType.ENUM, diagramView.getDiagram(), Visibility.PUBLIC);
		
		EnumPainter enumPainter = new EnumPainter(enumeracija, e.getX(), e.getY());
		
		for(ElementPainter ep: diagramView.getElementPainters()){
		
		}
		
		enumPainter.addElement(enumeracija);
		diagramView.getElementPainters().add(enumPainter);
	}
	
	@Override
	public void mouseDragged(MouseEvent e, DiagramView diagramView) {
	
	}
	
	@Override
	public void mouseRelease(MouseEvent e, DiagramView diagramView) {
	
	}
}
