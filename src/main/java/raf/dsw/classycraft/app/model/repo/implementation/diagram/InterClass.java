package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;

import java.awt.*;
import java.util.ArrayList;

public abstract class InterClass extends DiagramElement {
	private Visibility visibility;
	private int x;
	private int y;
	private int currentWidth;
	private int currentHeight;
	private ArrayList<Point> connectionPoints;
	
	
	public InterClass(ClassyNodeComposite parent, String name, Visibility visibility, int x, int y) {
		super(parent, name);
		this.visibility = visibility;
		this.x = x;
		this.y = y;
		generatePoints();
		
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
	
	public int getCurrentWidth() {
		return currentWidth;
	}
	
	public void setCurrentWidth(int currentWidth) {
		this.currentWidth = currentWidth;
		generatePoints();
		
	}
	
	public int getCurrentHeight() {
		return currentHeight;
	}
	
	public void setCurrentHeight(int currentHeight) {
		this.currentHeight = currentHeight;
		generatePoints();
	}
	
	public ArrayList<Point> getConnectionPoints() {
		return connectionPoints;
	}
	
	public void setConnectionPoints(ArrayList<Point> connectionPoints) {
		this.connectionPoints = connectionPoints;
	}
	
	public Visibility getVisibility() {
		return visibility;
	}
	
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	
	public void generatePoints() {
		
		connectionPoints = new ArrayList<>();
		
		connectionPoints.add(new Point(x, y));
		connectionPoints.add(new Point(x + currentWidth, y));
		connectionPoints.add(new Point(x, y + currentHeight));
		connectionPoints.add(new Point(x + currentWidth, y + currentHeight));
		
		connectionPoints.add(midPoint(connectionPoints.get(0), connectionPoints.get(1)));
		connectionPoints.add(midPoint(connectionPoints.get(0), connectionPoints.get(2)));
		connectionPoints.add(midPoint(connectionPoints.get(3), connectionPoints.get(2)));
		connectionPoints.add(midPoint(connectionPoints.get(3), connectionPoints.get(1)));
		
		connectionPoints.add(midPoint(connectionPoints.get(0), connectionPoints.get(4)));
		connectionPoints.add(midPoint(connectionPoints.get(0), connectionPoints.get(5)));
		connectionPoints.add(midPoint(connectionPoints.get(1), connectionPoints.get(4)));
		connectionPoints.add(midPoint(connectionPoints.get(1), connectionPoints.get(7)));
		connectionPoints.add(midPoint(connectionPoints.get(2), connectionPoints.get(6)));
		connectionPoints.add(midPoint(connectionPoints.get(2), connectionPoints.get(5)));
		connectionPoints.add(midPoint(connectionPoints.get(3), connectionPoints.get(7)));
		connectionPoints.add(midPoint(connectionPoints.get(3), connectionPoints.get(6)));
		
		connectionPoints.add(midPoint(connectionPoints.get(0), connectionPoints.get(8)));
		connectionPoints.add(midPoint(connectionPoints.get(0), connectionPoints.get(9)));
		connectionPoints.add(midPoint(connectionPoints.get(1), connectionPoints.get(10)));
		connectionPoints.add(midPoint(connectionPoints.get(1), connectionPoints.get(11)));
		connectionPoints.add(midPoint(connectionPoints.get(2), connectionPoints.get(13)));
		connectionPoints.add(midPoint(connectionPoints.get(2), connectionPoints.get(12)));
		connectionPoints.add(midPoint(connectionPoints.get(3), connectionPoints.get(15)));
		connectionPoints.add(midPoint(connectionPoints.get(3), connectionPoints.get(14)));
		connectionPoints.add(midPoint(connectionPoints.get(8), connectionPoints.get(4)));
		connectionPoints.add(midPoint(connectionPoints.get(10), connectionPoints.get(4)));
		connectionPoints.add(midPoint(connectionPoints.get(9), connectionPoints.get(5)));
		connectionPoints.add(midPoint(connectionPoints.get(13), connectionPoints.get(5)));
		connectionPoints.add(midPoint(connectionPoints.get(12), connectionPoints.get(6)));
		connectionPoints.add(midPoint(connectionPoints.get(15), connectionPoints.get(6)));
		connectionPoints.add(midPoint(connectionPoints.get(11), connectionPoints.get(7)));
		connectionPoints.add(midPoint(connectionPoints.get(14), connectionPoints.get(7)));
		
		connectionPoints.remove(0);
		connectionPoints.remove(0);
		connectionPoints.remove(0);
		connectionPoints.remove(0);
		
	}
	
	public Point closestConnectionPoint(Point point) {
		Point min = connectionPoints.get(0);
		for(Point p : connectionPoints) {
			if(point.distance(p) < point.distance(min)) {
				min = p;
			}
		}
		return min;
	}
	
	public Point midPoint(Point a, Point b) {
		return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
	}
}
