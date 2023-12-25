package main.java.raf.dsw.classycraft.app.state.concrete;

import main.java.raf.dsw.classycraft.app.command.implementation.DeleteElementCommand;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.connection_painter.ConnectionPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.interclass_painter.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.state.State;
import main.java.raf.dsw.classycraft.app.state.StateImplement;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class RemoveState extends StateImplement implements State {
    @Override
    public void mousePressed(MouseEvent e, DiagramView diagramView) {
        ArrayList<ElementPainter> removedElements = new ArrayList<>();
        for (ElementPainter elementPainter : diagramView.getElementPainters()) {
            if (elementPainter.contains(diagramView.adjustPoint(e.getPoint()).x, diagramView.adjustPoint(e.getPoint()).y)) {
                removedElements.add(elementPainter);
                break;
            }
        }
        if (removedElements.size() == 0 || !(removedElements.get(0) instanceof InterClassPainter))
            return;
        ElementPainter removedElement = removedElements.get(0);
        for (int i = 0; i < diagramView.getElementPainters().size(); i++) {
            ElementPainter elementPainter = diagramView.getElementPainters().get(i);
            if (elementPainter instanceof ConnectionPainter && (((ConnectionPainter) elementPainter).getDiagramElement().getFrom() == removedElement.getDiagramElement() || ((ConnectionPainter) elementPainter).getDiagramElement().getTo() == removedElement.getDiagramElement())) {
                removedElements.add(elementPainter);
            }
        }
        diagramView.getCommandManager().addCommand(new DeleteElementCommand(removedElements));
    }

    @Override
    public void mouseDragged(MouseEvent e, DiagramView diagramView) {

    }

    @Override
    public void mouseRelease(MouseEvent e, DiagramView diagramView) {

    }
}
