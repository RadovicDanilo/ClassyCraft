package main.java.raf.dsw.classycraft.app.gui.swing.view.painter;

import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;

public class InterClassPainter extends ElementPainter{
	private int x;
	private int y;
	public InterClassPainter(DiagramElement diagramElement,int x,int y) {
		super(diagramElement);
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
