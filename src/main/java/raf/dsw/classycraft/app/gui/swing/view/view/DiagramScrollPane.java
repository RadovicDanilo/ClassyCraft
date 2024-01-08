package main.java.raf.dsw.classycraft.app.gui.swing.view.view;

import main.java.raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class DiagramScrollPane extends JPanel implements ISubscriber, ComponentListener {
    private final JScrollBar horizontalScrollBar;
    private final JScrollBar verticalScrollBar;
    private DiagramView diagramView;

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
        horizontalScrollBar.setValue(0);
        verticalScrollBar.setValue(0);
        this.add(verticalScrollBar, BorderLayout.EAST);
        this.add(horizontalScrollBar, BorderLayout.SOUTH);

    }

    @Override
    public void update(Object notification) {

    }

    public DiagramView getDiagramView() {
        return diagramView;
    }

    public void setDiagramView(DiagramView diagramView) {
        this.diagramView = diagramView;
    }

    public JScrollBar getHorizontalScrollBar() {
        return horizontalScrollBar;
    }

    public JScrollBar getVerticalScrollBar() {
        return verticalScrollBar;
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

