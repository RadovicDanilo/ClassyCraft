package main.java.raf.dsw.classycraft.app.gui.swing.view.view;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.observer.IPublisher;
import main.java.raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class DiagramView extends JPanel implements ISubscriber, IPublisher, AdjustmentListener {
	public final BasicStroke strokeDashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f);
	private final ArrayList<ISubscriber> subscribers;
	private final ArrayList<ElementPainter> elementPainters;
	//TODO X
	int step;
	private Diagram diagram;
	private Point connectionFrom;
	private Point connectionTo;
	private Point selectFrom;
	private Point selectTo;
	private ArrayList<ElementPainter> selected;
	private AffineTransform affineTransform;
	private double scaleFactor;
	private int prevVerticalScrollVal;
	private int prevHorizontalScrollVal;
	private double initialScale;
	
	public DiagramView(Diagram diagram) {
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter(this);
		this.addMouseListener(myMouseAdapter);
		this.addMouseMotionListener(myMouseAdapter);
		this.addMouseWheelListener(myMouseAdapter);
		this.diagram = diagram;
		diagram.addSubscriber(this);
		
		super.setPreferredSize(new Dimension(10, 10));
		
		elementPainters = new ArrayList<>();
		
		connectionFrom = null;
		connectionTo = null;
		
		selectFrom = null;
		selectTo = null;
		
		selected = new ArrayList<>();
		subscribers = new ArrayList<>();
		step = 1;
		initialScale = 0;
		
		
	}
	
	//TODO X
	public int correctMouseX(int x) {
		x -= affineTransform.getTranslateX() / initialScale;
		x *= initialScale / affineTransform.getScaleX();
		return x;
	}
	
	public int correctMouseY(int y) {
		y -= affineTransform.getTranslateY() / initialScale;
		y *= initialScale / affineTransform.getScaleX();
		return y;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//TODO X;
		if(initialScale == 0) {
			affineTransform = g2d.getTransform();
			initialScale = affineTransform.getScaleX();
			step = (int) (step / initialScale);
		}
		g2d.setTransform(affineTransform);
		
		((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent())
			.getHorizontalScrollBar().setMaximum((int) (affineTransform.getScaleX() + Math.max(128, getLowerRightPoint().x)));
		((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent())
			.getVerticalScrollBar().setMaximum(((int) affineTransform.getScaleY() + Math.max(128, getLowerRightPoint().y)));
		
		
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
			double x = affineTransform.getTranslateX() + (prevHorizontalScrollVal - e.getValue()) * affineTransform.getScaleX();
			affineTransform.translate((prevHorizontalScrollVal - e.getValue()) * affineTransform.getScaleX(), 0);
			affineTransform.translate((x - affineTransform.getTranslateX()), 0);
			prevHorizontalScrollVal = e.getValue();
		}else {
			double x = affineTransform.getTranslateY() + (prevVerticalScrollVal - e.getValue()) * affineTransform.getScaleX();
			affineTransform.translate(0, (prevVerticalScrollVal - e.getValue()) * affineTransform.getScaleX());
			affineTransform.translate(0, (x - affineTransform.getTranslateY()));
			prevVerticalScrollVal = e.getValue();
		}
		DiagramScrollPane diagramScrollPane = (DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent();
		if(diagramScrollPane.getVerticalScrollBar().getValue() == 0 && diagramScrollPane.getHorizontalScrollBar().getValue() == 0) {
			AffineTransform at = new AffineTransform();
			at.scale(affineTransform.getScaleX(), affineTransform.getScaleY());
			affineTransform.setTransform(at);
		}
		repaint();
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
	
	
	public void setSelectFrom(Point selectFrom) {
		repaint();
		this.selectFrom = selectFrom;
	}
	
	
	public void setSelectTo(Point selectTo) {
		repaint();
		this.selectTo = selectTo;
	}
	
	
	public void setConnectionFrom(Point connectionFrom) {
		repaint();
		this.connectionFrom = connectionFrom;
	}
	
	
	public void setConnectionTo(Point connectionTo) {
		repaint();
		this.connectionTo = connectionTo;
	}
	
	
	@Override
	public void addSubscriber(ISubscriber sub) {
		if(!subscribers.contains(sub))
			subscribers.add(sub);
	}
	
	@Override
	public void removeSubscriber(ISubscriber sub) {
		subscribers.remove(sub);
	}
	
	@Override
	public void notifySubscribers(Object notification) {
		for(ISubscriber sub : subscribers)
			sub.update("");
	}
	
	public BasicStroke getStrokeDashed() {
		return strokeDashed;
	}
	
	public ArrayList<ISubscriber> getSubscribers() {
		return subscribers;
	}
	
	public int getStep() {
		return step;
	}
	
	public void setStep(int step) {
		this.step = step;
	}
	
	public Point getConnectionFrom() {
		return connectionFrom;
	}
	
	public Point getConnectionTo() {
		return connectionTo;
	}
	
	public Point getSelectFrom() {
		return selectFrom;
	}
	
	public Point getSelectTo() {
		return selectTo;
	}
	
	public AffineTransform getAffineTransform() {
		return affineTransform;
	}
	
	public void setAffineTransform(AffineTransform affineTransform) {
		this.affineTransform = affineTransform;
	}
	
	public double getScaleFactor() {
		return scaleFactor;
	}
	
	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
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
	
	public double getInitialScale() {
		return initialScale;
	}
	
	public void setInitialScale(double initialScale) {
		this.initialScale = initialScale;
	}
}

