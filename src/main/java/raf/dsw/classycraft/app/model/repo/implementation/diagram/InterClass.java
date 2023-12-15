package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;

import java.awt.*;
import java.util.ArrayList;

public abstract class InterClass extends DiagramElement {
    private int x;
    private int y;
    private int currentWidth;
    private int currentHeight;
    private ArrayList<Point> connectionPoints;


    public InterClass(ClassyNodeComposite parent, String name, int x, int y) {
        super(parent, name);
        this.x = x;
        this.y = y;
        generatePoints();

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < 0)
            return;
        this.x = x;
        generatePoints();
        ((Diagram) getParent()).notifySubscribers("");
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y < 0)
            return;
        this.y = y;
        generatePoints();
        ((Diagram) getParent()).notifySubscribers("");
    }

    public int getCurrentWidth() {
        return currentWidth;
    }

    public void setCurrentWidth(int currentWidth) {
        this.currentWidth = currentWidth;
        generatePoints();
        ((Diagram) getParent()).notifySubscribers("");

    }

    public int getCurrentHeight() {
        return currentHeight;
    }

    public void setCurrentHeight(int currentHeight) {
        this.currentHeight = currentHeight;
        generatePoints();
        ((Diagram) getParent()).notifySubscribers("");
    }

    public ArrayList<Point> getConnectionPoints() {
        return connectionPoints;
    }

    public void generatePoints() {

        connectionPoints = new ArrayList<>();

        connectionPoints.add(new Point(x, y));
        connectionPoints.add(new Point(x + currentWidth, y));
        connectionPoints.add(new Point(x, y + currentHeight));
        connectionPoints.add(new Point(x + currentWidth, y + currentHeight));

        connectionPoints.add(midPoint(connectionPoints.get(0), connectionPoints.get(1)));
        connectionPoints.add(midPoint(connectionPoints.get(0), connectionPoints.get(2)));
        connectionPoints.add(midPoint(connectionPoints.get(3), connectionPoints.get(2)));
        connectionPoints.add(midPoint(connectionPoints.get(3), connectionPoints.get(1)));

        connectionPoints.add(midPoint(connectionPoints.get(0), connectionPoints.get(4)));
        connectionPoints.add(midPoint(connectionPoints.get(0), connectionPoints.get(5)));
        connectionPoints.add(midPoint(connectionPoints.get(1), connectionPoints.get(4)));
        connectionPoints.add(midPoint(connectionPoints.get(1), connectionPoints.get(7)));
        connectionPoints.add(midPoint(connectionPoints.get(2), connectionPoints.get(6)));
        connectionPoints.add(midPoint(connectionPoints.get(2), connectionPoints.get(5)));
        connectionPoints.add(midPoint(connectionPoints.get(3), connectionPoints.get(7)));
        connectionPoints.add(midPoint(connectionPoints.get(3), connectionPoints.get(6)));

        connectionPoints.add(midPoint(connectionPoints.get(0), connectionPoints.get(8)));
        connectionPoints.add(midPoint(connectionPoints.get(0), connectionPoints.get(9)));
        connectionPoints.add(midPoint(connectionPoints.get(1), connectionPoints.get(10)));
        connectionPoints.add(midPoint(connectionPoints.get(1), connectionPoints.get(11)));
        connectionPoints.add(midPoint(connectionPoints.get(2), connectionPoints.get(13)));
        connectionPoints.add(midPoint(connectionPoints.get(2), connectionPoints.get(12)));
        connectionPoints.add(midPoint(connectionPoints.get(3), connectionPoints.get(15)));
        connectionPoints.add(midPoint(connectionPoints.get(3), connectionPoints.get(14)));
        connectionPoints.add(midPoint(connectionPoints.get(8), connectionPoints.get(4)));
        connectionPoints.add(midPoint(connectionPoints.get(10), connectionPoints.get(4)));
        connectionPoints.add(midPoint(connectionPoints.get(9), connectionPoints.get(5)));
        connectionPoints.add(midPoint(connectionPoints.get(13), connectionPoints.get(5)));
        connectionPoints.add(midPoint(connectionPoints.get(12), connectionPoints.get(6)));
        connectionPoints.add(midPoint(connectionPoints.get(15), connectionPoints.get(6)));
        connectionPoints.add(midPoint(connectionPoints.get(11), connectionPoints.get(7)));
        connectionPoints.add(midPoint(connectionPoints.get(14), connectionPoints.get(7)));

        connectionPoints.remove(0);
        connectionPoints.remove(0);
        connectionPoints.remove(0);
        connectionPoints.remove(0);

    }

    public Point closestConnectionPoint(Point point) {
        Point min = connectionPoints.get(0);
        for (Point p : connectionPoints) {
            if (point.distance(p) < point.distance(min)) {
                min = p;
            }
        }
        return min;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof InterClass))
            return false;
        return this.getName().equals(((InterClass) obj).getName());
    }

    public Point midPoint(Point a, Point b) {
        return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
    }
}
