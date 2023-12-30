package main.java.raf.dsw.classycraft.app.state.concrete.draw_class;

import main.java.raf.dsw.classycraft.app.command.implementation.DrawElementCommand;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.connection_painter.DependencyPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.interclass_painter.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.ElementFactory;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.element_type.ConnectionType;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Dependency;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

import java.awt.event.MouseEvent;

public class DrawDependencyState extends DrawConnectionState {
    @Override
    public void mousePressed(MouseEvent e, DiagramView diagramView) {
        super.mousePressed(e, diagramView);
    }

    @Override
    public void mouseDragged(MouseEvent e, DiagramView diagramView) {
        super.mouseDragged(e, diagramView);

    }

    @Override
    public void mouseRelease(MouseEvent e, DiagramView diagramView) {
        diagramView.setConnectionTo(null);
        diagramView.setConnectionFrom(null);
        for (ElementPainter ep : diagramView.getElementPainters()) {
            if (ep instanceof InterClassPainter && ((InterClassPainter) ep).getRectangle().contains(diagramView.adjustPoint(e.getPoint()))) {
                ElementFactory elementFactory = new ElementFactory();
                Dependency connection = (Dependency) elementFactory.createConnection(ConnectionType.DEPENDENCY, diagramView.getDiagram(), getFrom().getDiagramElement(), (InterClass) ep.getDiagramElement());
                DependencyPainter painter = new DependencyPainter(connection);

                if (diagramView.getElementPainters().contains(painter)) {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.CONNECTION_ALREADY_EXISTS);
                    return;
                }
                diagramView.getCommandManager().addCommand(new DrawElementCommand(painter));
                return;
            }
        }
    }
}
