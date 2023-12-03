package main.java.raf.dsw.classycraft.app.gui.swing.view.painter;

import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;

public class ConnectionPainter extends ElementPainter{
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	
	public ConnectionPainter(DiagramElement diagramElement, int startX, int startY, int endX, int endY) {
		super(diagramElement);
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}
	
}
