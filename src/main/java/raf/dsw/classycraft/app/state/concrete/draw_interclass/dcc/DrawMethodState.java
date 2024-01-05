package main.java.raf.dsw.classycraft.app.state.concrete.draw_interclass.dcc;

import main.java.raf.dsw.classycraft.app.command.implementation.DrawMethodCommand;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.interclass_painter.ClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.interclass_painter.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.interclass_painter.InterfacePainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Method;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class DrawMethodState extends DrawClassContentState {

    @Override
    public void mousePressed(MouseEvent e, DiagramView diagramView) {
        for (ElementPainter elementPainter : ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView().getElementPainters()) {
            if (!(elementPainter instanceof InterClassPainter) || !((InterClassPainter) elementPainter).getRectangle().contains(diagramView.adjustPoint(e.getPoint()))) {
                continue;
            }
            if (elementPainter instanceof ClassPainter) {
                String name = JOptionPane.showInputDialog("Method name:");
                String returnValue = JOptionPane.showInputDialog("return value:");
                Visibility visibility = (Visibility) JOptionPane.showInputDialog(null, "Visibility", "Visibility", JOptionPane.QUESTION_MESSAGE, null, Visibility.values(), Visibility.PUBLIC);
                if (name == null || name.length() == 0 || !name.substring(0, 1).matches("[a-zA-Z]+") || !name.matches("^([\\w+\\-/])+$")) {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.METHOD_NAME_NOT_VALID);
                    return;
                }
                if (returnValue == null || returnValue.length() == 0 || !returnValue.substring(0, 1).matches("[a-zA-Z]+") || !returnValue.matches("^([\\w+\\-/])+$")) {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.METHOD_RETURN_VALUE_NOT_VALID);
                    return;
                }
                diagramView.getCommandManager().addCommand(new DrawMethodCommand((Klasa) elementPainter.getDiagramElement(), new Method(name, visibility, returnValue)));

            }
            if (elementPainter instanceof InterfacePainter) {
                String name = JOptionPane.showInputDialog("Method name:");
                String returnValue = JOptionPane.showInputDialog("return value:");
                Visibility visibility = (Visibility) JOptionPane.showInputDialog(null, "Visibility", "Visibility", JOptionPane.QUESTION_MESSAGE, null, Visibility.values(), Visibility.PUBLIC);
                if (name == null || name.length() == 0 || !name.substring(0, 1).matches("[a-zA-Z]+") || !name.matches("^([\\w+\\-/])+$")) {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.METHOD_NAME_NOT_VALID);
                    return;
                }
                if (returnValue == null || returnValue.length() == 0 || !returnValue.substring(0, 1).matches("[a-zA-Z]+") || !returnValue.matches("^([\\w+\\-/])+$")) {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.METHOD_RETURN_VALUE_NOT_VALID);
                    return;
                }
                diagramView.getCommandManager().addCommand(new DrawMethodCommand((Interface) elementPainter.getDiagramElement(), new Method(name, visibility, returnValue)));

            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e, DiagramView diagramView) {

    }

    @Override
    public void mouseRelease(MouseEvent e, DiagramView diagramView) {

    }
}
