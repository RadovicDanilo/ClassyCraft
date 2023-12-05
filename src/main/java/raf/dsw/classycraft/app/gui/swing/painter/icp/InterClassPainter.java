package main.java.raf.dsw.classycraft.app.gui.swing.painter.icp;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;

import java.awt.*;
import java.util.ArrayList;

public abstract class InterClassPainter extends ElementPainter {
	private int x;
	private int y;
	private int currentWidth;
	private int currentHeight;
	private ArrayList<Point> connectionPoints;
	
	public InterClassPainter(DiagramElement diagramElement, int x, int y) {
		super(diagramElement);
		this.x = x;
		this.y = y;
		currentHeight = 0;
		currentWidth = 0;
		generatePoints();
	}
	
	public boolean intersects(Rectangle r) {
		return r.intersects(this.getRectangle());
	}
	@Override
	public boolean contains(Point p) {
		return getRectangle().contains(p);
	}
	
	public Rectangle getRectangle() {
		Rectangle r = new Rectangle();
		r.setSize(currentWidth, currentHeight);
		r.setLocation(x, y);
		return r;
	}
	
	public Point midPoint(Point a, Point b) {
		return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
	}
	
	public void generatePoints() {
		
		connectionPoints = new ArrayList<>();
		
		connectionPoints.add(new Point(x, y));
		connectionPoints.add(new Point(x + currentWidth, y));
		connectionPoints.add(new Point(x, y + currentHeight));
		connectionPoints.add(new Point(x + currentWidth, y + currentHeight));
		
		connectionPoints.add(midPoint(connectionPoints.get(0) , connectionPoints.get(1)));
		connectionPoints.add(midPoint(connectionPoints.get(0) , connectionPoints.get(2)));
		connectionPoints.add(midPoint(connectionPoints.get(3) , connectionPoints.get(2)));
		connectionPoints.add(midPoint(connectionPoints.get(3) , connectionPoints.get(1)));
		
		connectionPoints.add(midPoint(connectionPoints.get(0) , connectionPoints.get(4)));
		connectionPoints.add(midPoint(connectionPoints.get(0) , connectionPoints.get(5)));
		connectionPoints.add(midPoint(connectionPoints.get(1) , connectionPoints.get(4)));
		connectionPoints.add(midPoint(connectionPoints.get(1) , connectionPoints.get(7)));
		connectionPoints.add(midPoint(connectionPoints.get(2) , connectionPoints.get(6)));
		connectionPoints.add(midPoint(connectionPoints.get(2) , connectionPoints.get(5)));
		connectionPoints.add(midPoint(connectionPoints.get(3) , connectionPoints.get(7)));
		connectionPoints.add(midPoint(connectionPoints.get(3) , connectionPoints.get(6)));
		
		
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
		generatePoints();
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
		generatePoints();
	}
	
	public void setCurrentHeight(int currentHeight) {
		this.currentHeight = currentHeight;
		generatePoints();
		
	}
	
	public void setCurrentWidth(int currentWidth) {
		this.currentWidth = currentWidth;
		generatePoints();
	}
	
	public int getCurrentWidth() {
		return currentWidth;
	}
	
	public int getCurrentHeight() {
		return currentHeight;
	}
	
	public ArrayList<Point> getConnectionPoints() {
		return connectionPoints;
	}
	
	public void setConnectionPoints(ArrayList<Point> connectionPoints) {
		this.connectionPoints = connectionPoints;
	}
	
	public Point closestConnectionPoint(Point point) {
		Point min = connectionPoints.get(0);
		for(Point p: connectionPoints){
			if(point.distance(p) < point.distance(min)){
				min = p;
			}
		}
		return min;
	}
}
