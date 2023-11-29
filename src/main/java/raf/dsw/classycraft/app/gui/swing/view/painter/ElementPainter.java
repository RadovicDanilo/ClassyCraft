package main.java.raf.dsw.classycraft.app.gui.swing.view.painter;

import java.awt.*;

public abstract class ElementPainter {
	private Point starPoint;
	
	public ElementPainter(Point starPoint) {
		this.starPoint = starPoint;
	}
	
	public void draw(Graphics2D g){
	
	}
	
	
	public Point getStarPoint() {
		return starPoint;
	}
	
	public void setStarPoint(Point starPoint) {
		this.starPoint = starPoint;
	}
}
