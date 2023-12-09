package main.java.raf.dsw.classycraft.app.state.concrete;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.ClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.EnumPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterfacePainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.ElementFactory;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.enumeration.InterClassType;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.ClassContent;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Method;
import main.java.raf.dsw.classycraft.app.state.State;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DuplicateState implements State {
	@Override
	public void mousePressed(MouseEvent e, DiagramView diagramView) {
		for(ElementPainter elementPainter : diagramView.getElementPainters()) {
			if(elementPainter instanceof InterClassPainter && elementPainter.contains(diagramView.correctMouseX(e.getX()), diagramView.correctMouseY(e.getY()))) {
				duplicate(elementPainter, diagramView);
				break;
			}
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void duplicate(ElementPainter elementPainter, DiagramView diagramView) {
		DiagramElement diagramElement = elementPainter.getDiagramElement();
		ElementFactory ef = new ElementFactory();
		if(diagramElement instanceof Klasa) {
			Klasa c = (Klasa) ef.createInterClass(InterClassType.CLASS, (ClassyNodeComposite) diagramElement.getParent(), ((Klasa) diagramElement).getVisibility(), ((Klasa) diagramElement).getX() + 15, ((Klasa) diagramElement).getY() + 15);
			
			c.setContents((ArrayList<ClassContent>) ((Klasa) diagramElement).getContents().clone());
			
			c.setCurrentHeight(((Klasa) diagramElement).getCurrentHeight());
			c.setCurrentWidth(((Klasa) diagramElement).getCurrentWidth());
			
			ClassPainter painter = new ClassPainter(c);
			painter.addElement(c);
			
			diagramView.getElementPainters().add(painter);
		}else if(diagramElement instanceof Interface) {
			Interface i = (Interface) ef.createInterClass(InterClassType.INTERFACE, (ClassyNodeComposite) diagramElement.getParent(), ((Interface) diagramElement).getVisibility(), ((Interface) diagramElement).getX() + 15, ((Interface) diagramElement).getY() + 15);
			
			i.setMethods((ArrayList<Method>) ((Interface) diagramElement).getMethods().clone());
			
			i.setCurrentHeight(((Interface) diagramElement).getCurrentHeight());
			i.setCurrentWidth(((Interface) diagramElement).getCurrentWidth());
			
			InterfacePainter painter = new InterfacePainter(i);
			painter.addElement(i);
			
			diagramView.getElementPainters().add(painter);
		}else {
			Enum anEnum = (Enum) ef.createInterClass(InterClassType.ENUM, (ClassyNodeComposite) diagramElement.getParent(), ((Enum) diagramElement).getVisibility(), ((Enum) diagramElement).getX() + 15, ((Enum) diagramElement).getY() + 15);
			
			anEnum.setContents((ArrayList<String>) ((Enum) diagramElement).getContents().clone());
			
			anEnum.setCurrentHeight(((Enum) diagramElement).getCurrentHeight());
			anEnum.setCurrentWidth(((Enum) diagramElement).getCurrentWidth());
			
			EnumPainter painter = new EnumPainter(anEnum);
			painter.addElement(anEnum);
			
			diagramView.getElementPainters().add(painter);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e, DiagramView diagramView) {
	
	}
	
	@Override
	public void mouseRelease(MouseEvent e, DiagramView diagramView) {
	
	}
}
