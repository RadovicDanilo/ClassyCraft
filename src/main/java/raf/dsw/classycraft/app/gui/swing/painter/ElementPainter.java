package main.java.raf.dsw.classycraft.app.gui.swing.painter;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;

import java.awt.*;

public abstract class ElementPainter {

	private final DiagramElement diagramElement;
	private int currentWidth;
	private int currentHeight;
	
	public ElementPainter(DiagramElement diagramElement) {
		this.diagramElement = diagramElement;
	}
	public void addElement(DiagramElement element){
		ApplicationFramework.getInstance().getClassyRepository().addChild(element);
	}
	public void draw(Graphics2D g) {
	
	}
	
	public DiagramElement getDiagramElement() {
		return diagramElement;
	}
	
	public int getCurrentWidth() {
		return currentWidth;
	}
	
	public int getCurrentHeight() {
		return currentHeight;
	}
}
