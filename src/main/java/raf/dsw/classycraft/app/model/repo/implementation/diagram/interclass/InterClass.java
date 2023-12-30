package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;

import java.awt.*;
import java.util.ArrayList;

public abstract class InterClass extends DiagramElement {
    private int x;
    private int y;
    private int currentWidth;
    private int currentHeight;
    private Visibility visibility;

    @JsonIgnore

    private ArrayList<Point> connectionPoints;


    public InterClass(ClassyNodeComposite parent, String name, int x, int y, Visibility visibility) {
        super(parent, name);
        this.x = x;
        this.y = y;
        this.visibility = visibility;
        generatePoints();

    }

    public InterClass() {
        super();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        getParent().changed();
        if (x < 0)
            return;
        this.x = x;
        if (this.getParent() != null) {
            generatePoints();
            ((Diagram) getParent()).notifySubscribers("");
        }
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        getParent().changed();
        if (y < 0)
            return;
        this.y = y;
        if (this.getParent() != null) {
            generatePoints();
            ((Diagram) getParent()).notifySubscribers("");
        }
    }

    public int getCurrentWidth() {
        return currentWidth;
    }

    public void setCurrentWidth(int currentWidth) {
        this.currentWidth = currentWidth;
        if (this.getParent() != null) {
            generatePoints();
            ((Diagram) getParent()).notifySubscribers("");
        }

    }

    public int getCurrentHeight() {
        return currentHeight;
    }

    public void setCurrentHeight(int currentHeight) {
        this.currentHeight = currentHeight;
        if (this.getParent() != null) {
            generatePoints();
            ((Diagram) getParent()).notifySubscribers("");
        }
    }

    public ArrayList<Point> getConnectionPoints() {
        return connectionPoints;
    }

    public void setConnectionPoints(ArrayList<Point> connectionPoints) {
        this.connectionPoints = connectionPoints;
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

    public abstract void exportAsCode(String path, String packPath);

    public Point midPoint(Point a, Point b) {
        return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        getParent().changed();
        this.visibility = visibility;
    }
}
