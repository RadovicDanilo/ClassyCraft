package main.java.raf.dsw.classycraft.app.state.concrete.dic;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterfacePainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.ElementFactory;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.enumeration.InterClassType;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DrawInterfaceState extends DrawInterClassState {

    @Override
    public void mousePressed(MouseEvent e, DiagramView diagramView) {
        ElementFactory elementFactory = new ElementFactory();

        Interface anInterface = (Interface) elementFactory.createInterClass(InterClassType.INTERFACE, diagramView.getDiagram(),
                diagramView.adjustPoint(e.getPoint()).x, diagramView.adjustPoint(e.getPoint()).y);

        InterfacePainter interfacePainter = new InterfacePainter(anInterface);

        for (ElementPainter ep : ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView().getElementPainters()) {
            if (ep instanceof InterClassPainter) {
                Rectangle r = new Rectangle();
                int DEFAULT_HEIGHT = 40;
                int DEFAULT_WIDTH = 110;
                r.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                r.setLocation(diagramView.adjustPoint(e.getPoint()));
                if (ep.intersects(r)) {
                    return;
                }
            }
        }

        interfacePainter.addElement(anInterface);
        diagramView.getElementPainters().add(interfacePainter);
    }

    @Override
    public void mouseDragged(MouseEvent e, DiagramView diagramView) {

    }

    @Override
    public void mouseRelease(MouseEvent e, DiagramView diagramView) {

    }
}
