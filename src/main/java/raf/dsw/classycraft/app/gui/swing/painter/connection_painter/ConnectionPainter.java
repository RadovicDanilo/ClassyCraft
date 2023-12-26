package main.java.raf.dsw.classycraft.app.gui.swing.painter.connection_painter;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Connection;

import java.awt.*;
import java.awt.geom.Line2D;

public abstract class ConnectionPainter extends ElementPainter {

    public ConnectionPainter(Connection diagramElement) {
        super(diagramElement);

    }

    @Override
    public boolean contains(Point p) {
        Point a = getTwoClosestConnectionsPoints().getA();
        Point b = getTwoClosestConnectionsPoints().getB();
        double distance = Line2D.ptSegDistSq(a.x, a.y, b.x, b.y, p.x, p.y);
        return distance < 3;
    }

    @Override
    public Connection getDiagramElement() {
        return (Connection) super.getDiagramElement();
    }

    @Override
    public boolean contains(int x, int y) {
        return contains(new Point(x, y));
    }

    public PointPair getTwoClosestConnectionsPoints() {
        Point a = getDiagramElement().getFrom().getConnectionPoints().get(0);
        Point b = getDiagramElement().getTo().getConnectionPoints().get(0);
        for (Point p1 : getDiagramElement().getFrom().getConnectionPoints()) {
            for (Point p2 : getDiagramElement().getTo().getConnectionPoints()) {
                if (p1.distance(p2) < a.distance(b)) {
                    a = p1;
                    b = p2;
                }
            }
        }
        return new PointPair(a, b);
    }

    public boolean intersects(Rectangle r) {
        Point a = getTwoClosestConnectionsPoints().getA();
        Point b = getTwoClosestConnectionsPoints().getB();
        Line2D ln = new Line2D.Double(a, b);
        return ln.intersects(r);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ConnectionPainter && this.getDiagramElement().equals(((ConnectionPainter) obj).getDiagramElement());
    }
}
