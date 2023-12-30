package main.java.raf.dsw.classycraft.app.gui.swing.painter.connection_painter;

import java.awt.*;

public class PointPair {
    private Point a;
    private Point b;

    public PointPair(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public Point getA() {
        return a;
    }

    public void setA(Point a) {
        this.a = a;
    }

    public Point getB() {
        return b;
    }

    public void setB(Point b) {
        this.b = b;
    }
}
