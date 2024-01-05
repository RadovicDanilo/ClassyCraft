package main.java.raf.dsw.classycraft.app.gui.swing.painter;

import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;

import java.awt.*;

public abstract class ElementPainter {
    public final BasicStroke strokeDashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 8.0f, new float[]{8.0f}, 0.0f);
    public final BasicStroke normalStroke = new BasicStroke(1.0f);
    private final DiagramElement diagramElement;

    public ElementPainter(DiagramElement diagramElement) {
        this.diagramElement = diagramElement;
    }

    public Graphics2D setStroke(Graphics2D g) {
        if (((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView().getSelected().contains(this)) {
            g.setColor(Color.RED);
            g.setStroke(strokeDashed);
        } else {
            g.setColor(Color.BLACK);
            g.setStroke(normalStroke);
        }
        return g;
    }

    public abstract boolean contains(Point p);

    public void addElement() {
        MainFrame.getInstance().getClassyTree().addChild(MainFrame.getInstance().getClassyTree().getNode(diagramElement.getParent()), diagramElement);
    }

    public void draw(Graphics2D g) {

    }

    public DiagramElement getDiagramElement() {
        return diagramElement;
    }


    public abstract boolean intersects(Rectangle r);

    public abstract boolean contains(int x, int y);
}
