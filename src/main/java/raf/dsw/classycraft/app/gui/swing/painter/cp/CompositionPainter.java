package main.java.raf.dsw.classycraft.app.gui.swing.painter.cp;

import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;

import java.awt.*;

public class CompositionPainter extends ConnectionPainter {

    public CompositionPainter(Connection diagramElement) {
        super(diagramElement);
    }

    @Override
    public void addElement(DiagramElement element) {
        super.addElement(element);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        if (((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView().getSelected().contains(this)) {
            g.setColor(Color.RED);
            g.setStroke(strokeDashed);
        } else {
            g.setColor(Color.BLACK);
            g.setStroke(normalStroke);
        }
        Point b = getTwoClosestConnectionsPoints().getA();
        Point a = getTwoClosestConnectionsPoints().getB();
        g.drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());

        Point c = new Point(0, 0);
        Point d = new Point(0, 0);
        Point e = new Point(0, 0);

        Point unitVectorBToA = new Point(a.x - b.x, a.y - b.y);
        double intensityOfUnitVector = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
        unitVectorBToA.setLocation((double) 10 * (double) unitVectorBToA.x / intensityOfUnitVector, (double) 10 * (double) unitVectorBToA.y / intensityOfUnitVector);

        Point normalUnitVectorBToA = new Point(0, 0);
        if (unitVectorBToA.x == 0) {
            normalUnitVectorBToA.setLocation(5, 0);
        } else if (unitVectorBToA.y == 0) {
            normalUnitVectorBToA.setLocation(0, 5);
        } else {
            normalUnitVectorBToA.setLocation(1, -(double) unitVectorBToA.x / (double) unitVectorBToA.y);
            double intensityOfNormalUnitVector = Math.sqrt(1 + Math.pow((double) unitVectorBToA.x / (double) unitVectorBToA.y, 2));
            normalUnitVectorBToA.setLocation(5 / intensityOfNormalUnitVector, 5 * (double) normalUnitVectorBToA.y / intensityOfNormalUnitVector);
        }

        c.setLocation(b.x + unitVectorBToA.x + normalUnitVectorBToA.x, b.y + unitVectorBToA.y + normalUnitVectorBToA.y);
        d.setLocation(b.x + unitVectorBToA.x - normalUnitVectorBToA.x, b.y + unitVectorBToA.y - normalUnitVectorBToA.y);
        e.setLocation(b.x + 2 * unitVectorBToA.x, b.y + 2 * unitVectorBToA.y);
        Polygon diamond = new Polygon(new int[]{b.x, c.x, e.x, d.x}, new int[]{b.y, c.y, e.y, d.y}, 4);
        g.setColor(Color.BLACK);
        g.fill(diamond);

        if (((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView().getSelected().contains(this)) {
            g.setColor(Color.RED);
            g.setStroke(strokeDashed);
        } else {
            g.setColor(Color.BLACK);
            g.setStroke(normalStroke);
        }
        g.drawPolygon(diamond);
    }
}
