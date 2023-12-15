package main.java.raf.dsw.classycraft.app.gui.swing.view.view;

import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MyMouseAdapter extends MouseAdapter {
    private final DiagramView diagramView;

    public MyMouseAdapter(DiagramView diagramView) {
        this.diagramView = diagramView;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MainFrame.getInstance().getPackageView().mousePressed(e, diagramView);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MainFrame.getInstance().getPackageView().mouseRelease(e, diagramView);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MainFrame.getInstance().getPackageView().mouseDragged(e, diagramView);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        MainFrame.getInstance().getPackageView().mouseWheelMoved(e, diagramView);

    }
}

