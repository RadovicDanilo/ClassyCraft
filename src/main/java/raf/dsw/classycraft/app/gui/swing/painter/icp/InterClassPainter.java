package main.java.raf.dsw.classycraft.app.gui.swing.painter.icp;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;

import java.awt.*;
import java.util.ArrayList;

public abstract class InterClassPainter extends ElementPainter {
	
	public InterClassPainter(DiagramElement diagramElement) {
		super(diagramElement);
	}
	
	@Override
	public boolean intersects(Rectangle r) {
		return r.intersects(this.getRectangle());
	}
	
	@Override
	public boolean contains(Point p) {
		return getRectangle().contains(p);
	}
	
	@Override
	public InterClass getDiagramElement() {
		return (InterClass) super.getDiagramElement();
	}
	
	public Rectangle getRectangle() {
		Rectangle r = new Rectangle();
		r.setSize(getDiagramElement().getCurrentWidth(), getDiagramElement().getCurrentHeight());
		r.setLocation(getDiagramElement().getX(), getDiagramElement().getY());
		return r;
	}
	
	
	
	

	
	
}
