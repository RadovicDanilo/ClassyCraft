package main.java.raf.dsw.classycraft.app.state.concrete.draw_interclass;

import main.java.raf.dsw.classycraft.app.command.implementation.DrawElementCommand;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.interclass_painter.ClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.ElementFactory;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.element_type.InterClassType;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DrawClassState extends DrawInterClassState {

    @Override
    public void mousePressed(MouseEvent e, DiagramView diagramView) {

        ElementFactory elementFactory = new ElementFactory();
        Klasa klasa = (Klasa) elementFactory.createInterClass(InterClassType.CLASS, diagramView.getDiagram(), diagramView.adjustPoint(e.getPoint()).x, diagramView.adjustPoint(e.getPoint()).y);

        ClassPainter classPainter = new ClassPainter(klasa);
        for (ElementPainter ep : diagramView.getElementPainters()) {
            Rectangle r = new Rectangle();
            int DEFAULT_WIDTH = 110;
            int DEFAULT_HEIGHT = 40;
            r.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            r.setLocation(diagramView.adjustPoint(e.getPoint()));
            if (ep.intersects(r)) {
                return;
            }
        }
        diagramView.getCommandManager().addCommand(new DrawElementCommand(classPainter));
    }

    @Override
    public void mouseDragged(MouseEvent e, DiagramView diagramView) {

    }

    @Override
    public void mouseRelease(MouseEvent e, DiagramView diagramView) {

    }
}
