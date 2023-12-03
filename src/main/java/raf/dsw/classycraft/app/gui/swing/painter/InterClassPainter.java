package main.java.raf.dsw.classycraft.app.gui.swing.painter;

import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;

import java.awt.*;

public class InterClassPainter extends ElementPainter{
	private int x;
	private int y;
	private int currentWidth;
	private int currentHeight;
	public InterClassPainter(DiagramElement diagramElement,int x,int y) {
		super(diagramElement);
		this.x = x;
		this.y = y;
	}
	public boolean intersects(Rectangle r){
		return r.intersects(getRectangle());
	}
	public Rectangle getRectangle(){
		Rectangle r = new Rectangle();
		r.setSize(currentWidth,currentHeight);
		r.setLocation(x,y);
		return r;
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
	
	
	
	public void setCurrentWidth(int currentWidth) {
		this.currentWidth = currentWidth;
	}
	
	public int getCurrentWidth() {
		return currentWidth;
	}
	
	public int getCurrentHeight() {
		return currentHeight;
	}
	
	public void setCurrentHeight(int currentHeight) {
		this.currentHeight = currentHeight;
	}
}
