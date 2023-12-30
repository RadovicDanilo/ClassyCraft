package main.java.raf.dsw.classycraft.app.state.concrete.draw_interclass.dcc;

import main.java.raf.dsw.classycraft.app.command.implementation.DrawEnumFieldCommand;
import main.java.raf.dsw.classycraft.app.command.implementation.DrawFieldCommand;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.interclass_painter.ClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.interclass_painter.EnumPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.interclass_painter.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Field;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class DrawFieldState extends DrawClassContentState {
    @Override
    public void mousePressed(MouseEvent e, DiagramView diagramView) {
        for (ElementPainter elementPainter : diagramView.getElementPainters()) {
            if (!(elementPainter instanceof InterClassPainter) || !((InterClassPainter) elementPainter).getRectangle().contains(diagramView.adjustPoint(e.getPoint()))) {
                continue;
            }

            if (elementPainter instanceof ClassPainter) {
                String name = JOptionPane.showInputDialog("Field name:");
                String type = JOptionPane.showInputDialog("Field type:");
                Visibility visibility = (Visibility) JOptionPane.showInputDialog(null, "Visibility", "Visibility", JOptionPane.QUESTION_MESSAGE, null, Visibility.values(), Visibility.PUBLIC);

                if (name == null || name.length() == 0 || !name.substring(0, 1).matches("[a-zA-Z]+") || !name.matches("^([\\w+\\-/])+$")) {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.FIELD_NAME_NOT_VALID);
                    return;
                }
                if (type == null || type.length() == 0 || !type.substring(0, 1).matches("[a-zA-Z]+") || !type.matches("^([\\w+\\-/])+$")) {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.FIELD_VALUE_NOT_VALID);
                    return;
                }
                diagramView.getCommandManager().addCommand(new DrawFieldCommand((Klasa) elementPainter.getDiagramElement(), new Field(name, visibility, type)));
            }

            if (elementPainter instanceof EnumPainter) {
                String name = JOptionPane.showInputDialog("Enum name:");
                if (name == null || name.length() == 0 || !name.substring(0, 1).matches("[a-zA-Z]+") || !name.matches("^([\\w+\\-/])+$")) {
                    ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.ENUM_NOT_VALID);
                    return;
                }
                diagramView.getCommandManager().addCommand(new DrawEnumFieldCommand((Enum) elementPainter.getDiagramElement(), name));
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
