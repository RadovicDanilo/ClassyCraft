package main.java.raf.dsw.classycraft.app.gui.swing.view.view;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class DiagramView extends JPanel implements ISubscriber, AdjustmentListener {
	public final BasicStroke strokeDashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f);
	private final ArrayList<ElementPainter> elementPainters;
	
	private Diagram diagram;
	private Point connectionFrom;
	private Point connectionTo;
	private Point selectFrom;
	private Point selectTo;
	private ArrayList<ElementPainter> selected;
	
	
	private AffineTransform at;
	
	private double oldZoomFactor;
	private boolean zoomer;
	
	
	private Point zoomPoint;
	private Point oldZoomPoint;
	private double zoomFactor;
	
	
	private int prevVerticalScrollVal;
	private int prevHorizontalScrollVal;
	
	
	public DiagramView(Diagram diagram) {
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter(this);
		this.addMouseListener(myMouseAdapter);
		this.addMouseMotionListener(myMouseAdapter);
		this.addMouseWheelListener(myMouseAdapter);
		this.diagram = diagram;
		diagram.addSubscriber(this);
		
		elementPainters = new ArrayList<>();
		
		connectionFrom = null;
		connectionTo = null;
		
		selectFrom = null;
		selectTo = null;
		
		selected = new ArrayList<>();
		
		prevVerticalScrollVal = 0;
		prevHorizontalScrollVal = 0;
		
		zoomFactor = 1;
		zoomPoint = new Point();
		
		
		oldZoomFactor = 1;
		zoomer = true;
		
		
	}
	
	public void zoom(MouseWheelEvent e) {
		zoomPoint = e.getPoint();
		if(e.getWheelRotation() > 0) {
			zoomFactor -= 0.05;
		}else {
			zoomFactor += 0.05;
		}
		if(zoomFactor > 2)
			zoomFactor = 2;
		if(zoomFactor < 0.5)
			zoomFactor = 0.5;
		System.out.println("ZOOM FACTOR: " + zoomFactor);
		zoomer = true;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		if(zoomer) {
			at = g2d.getTransform();
			at.translate(getWidth() / 2, getHeight() / 2);
			at.setToScale(zoomFactor, zoomFactor);
			at.translate(-getWidth() / 2, -getHeight() / 2);
			
			zoomer = false;
		}
		g2d.setTransform(at);
		
		
		((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).
			getHorizontalScrollBar().setMaximum((int) (Math.max(128, getLowerRightPoint().x) / 2));
		
		((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).
			getHorizontalScrollBar().setVisibleAmount(64);
		
		((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).
			getVerticalScrollBar().setMaximum((int) (Math.max(128, getLowerRightPoint().y) / 2));
		
		((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).
			getVerticalScrollBar().setVisibleAmount(64);
		
		
		if(connectionFrom != null && connectionTo != null) {
			g2d.setStroke(strokeDashed);
			g2d.setColor(Color.RED);
			g2d.drawLine(connectionFrom.x, connectionFrom.y, connectionTo.x, connectionTo.y);
		}
		
		if(selectFrom != null && selectTo != null) {
			g2d.setStroke(strokeDashed);
			g2d.setColor(Color.RED);
			if(selectFrom.x < selectTo.x && selectFrom.y < selectTo.y) {
				g.drawRect(selectFrom.x, selectFrom.y, selectTo.x - selectFrom.x, selectTo.y - selectFrom.y);
			}else if(selectFrom.x > selectTo.x && selectFrom.y > selectTo.y) {
				g.drawRect(selectTo.x, selectTo.y, selectFrom.x - selectTo.x, selectFrom.y - selectTo.y);
			}else if(selectFrom.x > selectTo.x && selectFrom.y < selectTo.y) {
				g.drawRect(selectTo.x, selectFrom.y, selectFrom.x - selectTo.x, selectTo.y - selectFrom.y);
			}else {
				g.drawRect(selectFrom.x, selectTo.y, -selectFrom.x + selectTo.x, -selectTo.y + selectFrom.y);
			}
		}
		for(ElementPainter elementPainter : elementPainters) {
			elementPainter.draw((Graphics2D) g);
		}
	}
	
	
	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		if(((JScrollBar) e.getSource()).getOrientation() == Adjustable.HORIZONTAL) {
			at.translate(prevHorizontalScrollVal - e.getValue(), 0);
			prevHorizontalScrollVal = e.getValue();
		}else {
			at.translate(0, prevVerticalScrollVal - e.getValue());
			prevVerticalScrollVal = e.getValue();
		}
		DiagramScrollPane diagramScrollPane = (DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent();
		if(diagramScrollPane.getVerticalScrollBar().getValue() == 0 && diagramScrollPane.getHorizontalScrollBar().getValue() == 0) {
			AffineTransform at = new AffineTransform();
			at.scale(this.at.getScaleX(), this.at.getScaleY());
			this.at.setTransform(at);
			zoomer = true;
		}
		repaint();
	}
	
	
	public Point correctMouse(Point point) {
		//return new Point(correctMouseX(point.x), correctMouseY(point.y));
		try {
			Point2D p = at.inverseTransform(point, null);
			return new Point((int) (p.getX() + (zoomFactor - 1) / 100 * getWidth()), (int) (p.getY() + (zoomFactor - 1) / 100 * getHeight()));
		}catch(NoninvertibleTransformException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int correctMouseX(int x) {
		//x = (int) (x - at.getTranslateX());
		return x;
	}
	
	public int correctMouseY(int y) {
		y = (int) (y - at.getTranslateY());
		return y;
	}
	
	
	public Point getLowerRightPoint() {
		Point point = new Point(0, 0);
		for(ElementPainter elementPainter : elementPainters) {
			if(!(elementPainter instanceof InterClassPainter))
				continue;
			InterClass interClass = ((InterClassPainter) elementPainter).getDiagramElement();
			if(interClass.getX() + interClass.getCurrentWidth() > point.x)
				point.x = interClass.getX() + interClass.getCurrentWidth();
			if(interClass.getY() + interClass.getCurrentHeight() > point.y)
				point.y = interClass.getY() + interClass.getCurrentWidth();
		}
		return point;
	}
	
	
	public Rectangle getSelectionRectangle() {
		Rectangle r = new Rectangle();
		
		if(selectFrom != null && selectTo != null) {
			if(selectFrom.x < selectTo.x && selectFrom.y < selectTo.y) {
				r.setLocation(selectFrom.x, selectFrom.y);
				r.setSize(selectTo.x - selectFrom.x, selectTo.y - selectFrom.y);
			}else if(selectFrom.x > selectTo.x && selectFrom.y > selectTo.y) {
				r.setLocation(selectTo.x, selectTo.y);
				r.setSize(selectFrom.x - selectTo.x, selectFrom.y - selectTo.y);
			}else if(selectFrom.x > selectTo.x && selectFrom.y < selectTo.y) {
				r.setLocation(selectTo.x, selectFrom.y);
				r.setSize(selectFrom.x - selectTo.x, selectTo.y - selectFrom.y);
			}else {
				r.setLocation(selectFrom.x, selectTo.y);
				r.setSize(-selectFrom.x + selectTo.x, -selectTo.y + selectFrom.y);
			}
		}
		return r;
	}
	
	
	@Override
	public void update(Object notification) {
		if(notification instanceof DiagramElement) {
			for(ElementPainter elementPainter : elementPainters) {
				if(elementPainter.getDiagramElement() == notification) {
					elementPainters.remove(elementPainter);
					break;
				}
			}
		}
		repaint();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DiagramView) {
			return this.diagram.equals(((DiagramView) obj).getDiagram());
		}
		return false;
	}
	
	public Diagram getDiagram() {
		return diagram;
	}
	
	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}
	
	public void addSelectedElement(ElementPainter elementPainter) {
		if(!selected.contains(elementPainter)) {
			selected.add(elementPainter);
		}
		repaint();
	}
	
	public ArrayList<ElementPainter> getSelected() {
		return selected;
	}
	
	public void setSelected(ArrayList<ElementPainter> selected) {
		this.selected = selected;
		repaint();
	}
	
	public ArrayList<ElementPainter> getElementPainters() {
		return elementPainters;
	}
	
	public BasicStroke getStrokeDashed() {
		return strokeDashed;
	}
	
	
	public Point getConnectionFrom() {
		return connectionFrom;
	}
	
	public void setConnectionFrom(Point connectionFrom) {
		repaint();
		this.connectionFrom = connectionFrom;
	}
	
	public Point getConnectionTo() {
		return connectionTo;
	}
	
	public void setConnectionTo(Point connectionTo) {
		repaint();
		this.connectionTo = connectionTo;
	}
	
	public Point getSelectFrom() {
		return selectFrom;
	}
	
	public void setSelectFrom(Point selectFrom) {
		repaint();
		this.selectFrom = selectFrom;
	}
	
	public Point getSelectTo() {
		return selectTo;
	}
	
	public void setSelectTo(Point selectTo) {
		repaint();
		this.selectTo = selectTo;
	}
	
	public AffineTransform getAt() {
		return at;
	}
	
	public void setAt(AffineTransform at) {
		this.at = at;
	}
	
	
	public int getPrevVerticalScrollVal() {
		return prevVerticalScrollVal;
	}
	
	public void setPrevVerticalScrollVal(int prevVerticalScrollVal) {
		this.prevVerticalScrollVal = prevVerticalScrollVal;
	}
	
	public int getPrevHorizontalScrollVal() {
		return prevHorizontalScrollVal;
	}
	
	public void setPrevHorizontalScrollVal(int prevHorizontalScrollVal) {
		this.prevHorizontalScrollVal = prevHorizontalScrollVal;
	}
	
	
	public double getOldZoomFactor() {
		return oldZoomFactor;
	}
	
	public void setOldZoomFactor(double oldZoomFactor) {
		this.oldZoomFactor = oldZoomFactor;
	}
	
	public boolean isZoomer() {
		return zoomer;
	}
	
	public void setZoomer(boolean zoomer) {
		this.zoomer = zoomer;
	}
	
	public double getZoomFactor() {
		return zoomFactor;
	}
	
	public void setZoomFactor(double zoomFactor) {
		this.zoomFactor = zoomFactor;
	}
	
	
}

