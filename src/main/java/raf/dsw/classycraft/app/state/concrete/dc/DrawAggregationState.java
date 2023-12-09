package main.java.raf.dsw.classycraft.app.state.concrete.dc;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.cp.AggregationPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.cp.ConnectionPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.ElementFactory;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.enumeration.ConnectionType;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Aggregation;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DrawAggregationState extends DrawConnectionState {
	@Override
	public void mousePressed(MouseEvent e, DiagramView diagramView) {
		super.mousePressed(e, diagramView);
	}
	
	@Override
	public void mouseDragged(MouseEvent e, DiagramView diagramView) {
		super.mouseDragged(e, diagramView);
		
	}
	
	@Override
	public void mouseRelease(MouseEvent e, DiagramView diagramView) {
		diagramView.setConnectionTo(null);
		diagramView.setConnectionFrom(null);
		for(ElementPainter ep : diagramView.getElementPainters()) {
			if(ep instanceof InterClassPainter && ((InterClassPainter) ep).getRectangle().contains(new Point(diagramView.correctMouseX(e.getX()), diagramView.correctMouseY(e.getY())))) {
				
				ElementFactory elementFactory = new ElementFactory();
				Aggregation agr = (Aggregation) elementFactory.createConnection(ConnectionType.AGGREGATION, diagramView.getDiagram(), getFrom().getDiagramElement(), (InterClass) ep.getDiagramElement());
				AggregationPainter ap = new AggregationPainter(agr);
				
				for(ElementPainter elementPainter: diagramView.getElementPainters()){
					if(ap.equals(elementPainter)){
						//TODO SYSTEM EVENT
						break;
					}
					
				}
				
				ap.addElement(agr);
				diagramView.getElementPainters().add(ap);
				break;
			}
		}
	}
}
