package main.java.raf.dsw.classycraft.app.gui.swing.view.view;

import main.java.raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class DiagramScrollPane extends JPanel implements ISubscriber, ComponentListener {
	private DiagramView diagramView;
	private JScrollBar horizontalScrollBar;
	private JScrollBar verticalScrollBar;
	private ComponentEvent e;
	
	public DiagramScrollPane(DiagramView diagramView) {
		
		BorderLayout borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
		
		this.diagramView = diagramView;
		this.add(diagramView, BorderLayout.CENTER);
		
		horizontalScrollBar = new JScrollBar(Adjustable.HORIZONTAL);
		verticalScrollBar = new JScrollBar(Adjustable.VERTICAL);
		horizontalScrollBar.addAdjustmentListener(diagramView);
		verticalScrollBar.addAdjustmentListener(diagramView);
		horizontalScrollBar.setVisible(true);
		verticalScrollBar.setVisible(true);
		
		this.add(verticalScrollBar, BorderLayout.EAST);
		this.add(horizontalScrollBar, BorderLayout.SOUTH);

		diagramView.addSubscriber(this);
		
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

