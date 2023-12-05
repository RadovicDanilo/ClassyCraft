package main.java.raf.dsw.classycraft.app.state.concrete.dic;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.EnumPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.ElementFactory;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.enumeration.InterClassType;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DrawEnumState extends DrawInterClassState {
	private final int DEFAULT_HEIGHT = 40;
	private final int DEFAULT_WIDTH = 110;
	
	@Override
	public void mousePressed(MouseEvent e, DiagramView diagramView) {
		ElementFactory elementFactory = new ElementFactory();
		
		Enum enumeracija = (Enum) elementFactory.createInterClass(InterClassType.ENUM, diagramView.getDiagram(), Visibility.PUBLIC);
		
		EnumPainter enumPainter = new EnumPainter(enumeracija, e.getX(), e.getY());
		
		for(ElementPainter ep : ((DiagramView) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getElementPainters()) {
			if(ep instanceof InterClassPainter) {
				Rectangle r = new Rectangle();
				r.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
				r.setLocation(e.getPoint());
				if(((InterClassPainter) ep).intersects(r)) {
					return;
				}
			}
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
