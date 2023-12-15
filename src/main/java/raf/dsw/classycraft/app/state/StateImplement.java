package main.java.raf.dsw.classycraft.app.state;

import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;

import java.awt.event.MouseWheelEvent;

public abstract class StateImplement implements State {
    @Override
    public void mouseWheelMoved(MouseWheelEvent e, DiagramView diagramView) {
        diagramView.zoom(e);
    }
}
