package main.java.raf.dsw.classycraft.app.gui.swing.view.view;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.observer.ISubscriber;
import main.java.raf.dsw.classycraft.app.state.concrete.SelectState;

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
	private final Diagram diagram;
	private Point connectionFrom;
	private Point connectionTo;
	private Point selectFrom;
	private Point selectTo;
	private ArrayList<ElementPainter> selected;
	private AffineTransform at;
	private boolean zoomer;
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
		
		zoomer = true;
		repaint();
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		DiagramScrollPane dsp = ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent());
		
		float hPercent = 0;
		float vPercent = 0;
		try {
			hPercent = (float) dsp.getHorizontalScrollBar().getValue() / (float) (dsp.getHorizontalScrollBar().getMaximum() - dsp.getHorizontalScrollBar().getModel().getExtent());
			vPercent = (float) dsp.getVerticalScrollBar().getValue() / (float) (dsp.getVerticalScrollBar().getMaximum() - dsp.getVerticalScrollBar().getModel().getExtent());
		}catch(NullPointerException ignored) {
		
		}
		
		if(zoomer) {
			at = g2d.getTransform();
			at.translate((float) getWidth() / 2, (float) getHeight() / 2);
			at.setToScale(zoomFactor, zoomFactor);
			at.translate(-(float) getWidth() / 2, -(float) getHeight() / 2);
			zoomer = false;
			if(!(MainFrame.getInstance().getPackageView().getStateManager().getCurrentState() instanceof SelectState)) {
				dsp.getHorizontalScrollBar().setMaximum((int) ((Math.max(128, getLowerRightPoint().x)) * zoomFactor));
				dsp.getHorizontalScrollBar().setValue((int) (dsp.getHorizontalScrollBar().getMaximum() * hPercent));
				prevHorizontalScrollVal = (int) ((dsp.getHorizontalScrollBar().getMaximum() - dsp.getHorizontalScrollBar().getModel().getExtent()) * hPercent);
				
				dsp.getVerticalScrollBar().setMaximum((int) ((Math.max(128, getLowerRightPoint().y)) * zoomFactor));
				dsp.getVerticalScrollBar().setValue((int) (dsp.getVerticalScrollBar().getMaximum() * vPercent));
				prevVerticalScrollVal = (int) ((dsp.getVerticalScrollBar().getMaximum() - dsp.getVerticalScrollBar().getModel().getExtent()) * vPercent);
			}
		}


//		System.out.println("VERTICAL: " + vPercent);
//		System.out.println("HORIZONTAL: " + hPercent);
		g2d.setTransform(at);
		
		
		BasicStroke Border = new BasicStroke(5.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
		((Graphics2D) g).setStroke(Border);
		g.setColor(Color.RED);
		g.drawLine(0, 0, 0, 10000);
		g.drawLine(0, 0, 10000, 0);
		
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
			prevHorizontalScrollVal = e.getValue();
		}else {
			prevVerticalScrollVal = e.getValue();
		}
		AffineTransform at = new AffineTransform();
		at.scale(zoomFactor, zoomFactor);
		at.translate(-prevHorizontalScrollVal, -prevVerticalScrollVal);
		this.at.setTransform(at);
		repaint();
	}
	
	public void zoom(MouseWheelEvent e) {
		if(e.getWheelRotation() > 0) {
			zoomFactor -= 0.05;
		}else {
			zoomFactor += 0.05;
		}
		if(zoomFactor > 2) zoomFactor = 2;
		if(zoomFactor < 0.5) zoomFactor = 0.5;
		System.out.println("ZOOM FACTOR: " + zoomFactor);
		zoomer = true;
		repaint();
	}
	
	
	public Point adjustPoint(Point point) {
		try {
			Point2D p = at.inverseTransform(point, null);
			return new Point((int) (p.getX() + (zoomFactor - 1) / 500 * getWidth()), (int) (p.getY() + (zoomFactor - 1) / 500 * getHeight()));
		}catch(NoninvertibleTransformException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public Point getLowerRightPoint() {
		Point point = new Point(0, 0);
		for(ElementPainter elementPainter : elementPainters) {
			if(!(elementPainter instanceof InterClassPainter)) continue;
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
	
	
	public Point getConnectionFrom() {
		return connectionFrom;
	}
	
	public void setConnectionFrom(Point connectionFrom) {
		repaint();
		this.connectionFrom = connectionFrom;
	}
	
	public void setConnectionTo(Point connectionTo) {
		repaint();
		this.connectionTo = connectionTo;
	}
	
	public void setSelectFrom(Point selectFrom) {
		repaint();
		this.selectFrom = selectFrom;
	}
	
	public void setSelectTo(Point selectTo) {
		repaint();
		this.selectTo = selectTo;
	}
	
	
}

