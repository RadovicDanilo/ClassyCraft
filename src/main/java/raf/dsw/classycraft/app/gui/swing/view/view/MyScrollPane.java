package main.java.raf.dsw.classycraft.app.gui.swing.view.view;

import main.java.raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class MyScrollPane extends JPanel implements ISubscriber, ComponentListener {
	private DiagramView diagramView;
	private JScrollBar horizontalScrollBar;
	private JScrollBar verticalScrollBar;
	private ComponentEvent e;
	
	public MyScrollPane(DiagramView diagramView) {
		super();
		BorderLayout borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
		this.add(diagramView, BorderLayout.CENTER);
		this.diagramView = diagramView;
		horizontalScrollBar = new JScrollBar(0);
		verticalScrollBar = new JScrollBar(1);
		horizontalScrollBar.addAdjustmentListener((AdjustmentListener) diagramView);
		verticalScrollBar.addAdjustmentListener((AdjustmentListener) diagramView);
		this.add(verticalScrollBar, BorderLayout.EAST);
		this.add(horizontalScrollBar, BorderLayout.SOUTH);
		horizontalScrollBar.setVisible(true);
		verticalScrollBar.setVisible(true);
		//diagramView.addSubscriber(this);
		
	}
	
	@Override
	public void update(Object notification) {
	
	}
	
	
	@Override
	public void componentResized(ComponentEvent e) {
		diagramView.repaint();
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {
		diagramView.repaint();
	}
	
	@Override
	public void componentShown(ComponentEvent e) {
		diagramView.repaint();
	}
	
	@Override
	public void componentHidden(ComponentEvent e) {
		diagramView.repaint();
	}
	
	
}

