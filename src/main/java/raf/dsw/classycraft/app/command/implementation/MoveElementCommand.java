package main.java.raf.dsw.classycraft.app.command.implementation;

import main.java.raf.dsw.classycraft.app.command.AbstractCommand;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.connection_painter.ConnectionPainter;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.InterClass;

import java.awt.*;
import java.util.ArrayList;

public class MoveElementCommand extends AbstractCommand {
    private ArrayList<ElementPainter> elements;
    private Point start;
    private Point end;

    public MoveElementCommand(ArrayList<ElementPainter> elements, Point start, Point end) {
        this.elements = elements;
        this.start = start;
        this.end = end;
        for (ElementPainter e : elements) {
            if (e instanceof ConnectionPainter)
                continue;
            InterClass ic = (InterClass) e.getDiagramElement();
            ic.setX(ic.getX() - (end.x - start.x));
            ic.setY(ic.getY() - (end.y - start.y));
        }
    }

    @Override
    public void doCommand() {
        for (ElementPainter e : elements) {
            InterClass ic = (InterClass) e.getDiagramElement();
            ic.setX(ic.getX() + (end.x - start.x));
            ic.setY(ic.getY() + (end.y - start.y));
        }
    }

    @Override
    public void undoCommand() {
        for (ElementPainter e : elements) {
            InterClass ic = (InterClass) e.getDiagramElement();
            ic.setX(ic.getX() - (end.x - start.x));
            ic.setY(ic.getY() - (end.y - start.y));
        }
    }
}
