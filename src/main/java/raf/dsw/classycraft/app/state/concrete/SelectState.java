package main.java.raf.dsw.classycraft.app.state.concrete;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.state.State;
import main.java.raf.dsw.classycraft.app.state.StateImplement;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SelectState extends StateImplement implements State {
    private Point last = null;

    @Override
    public void mousePressed(MouseEvent e, DiagramView diagramView) {

        for (ElementPainter elementPainter : diagramView.getElementPainters()) {
            if (elementPainter.contains(diagramView.adjustPoint(e.getPoint()))) {
                last = diagramView.adjustPoint(e.getPoint());

                if (!diagramView.getSelected().contains(elementPainter)) {
                    diagramView.setSelected(new ArrayList<>());
                    diagramView.addSelectedElement(elementPainter);
                }
                return;
            }
        }
        diagramView.setSelected(new ArrayList<>());
    }

    @Override
    public void mouseDragged(MouseEvent e, DiagramView diagramView) {
        for (ElementPainter elementPainter : diagramView.getSelected()) {
            if (elementPainter instanceof InterClassPainter) {
                ((InterClassPainter) elementPainter).getDiagramElement().setX(((InterClassPainter) elementPainter).getDiagramElement().getX() + diagramView.adjustPoint(e.getPoint()).x - last.x);
                ((InterClassPainter) elementPainter).getDiagramElement().setY(((InterClassPainter) elementPainter).getDiagramElement().getY() + diagramView.adjustPoint(e.getPoint()).y - last.y);
            }
        }
        last = diagramView.adjustPoint(e.getPoint());

    }


    @Override
    public void mouseRelease(MouseEvent e, DiagramView diagramView) {
        last = null;
    }
}
