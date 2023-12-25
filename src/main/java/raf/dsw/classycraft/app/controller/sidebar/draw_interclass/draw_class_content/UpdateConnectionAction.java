package main.java.raf.dsw.classycraft.app.controller.sidebar.draw_interclass.draw_class_content;

import main.java.raf.dsw.classycraft.app.command.implementation.EditConnectionCommand;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Cardinality;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

import javax.swing.*;

public class UpdateConnectionAction {
    public UpdateConnectionAction(Connection k,
                                  JTextField tfName,
                                  JComboBox<Visibility> cbVisibility,
                                  JComboBox<Cardinality> cbCardinality) {
        if (tfName.getText() == null || tfName.getText().length() == 0 || !tfName.getText().substring(0, 1).matches("[a-zA-Z]+") || !tfName.getText().matches("^([\\w+\\-/])+$")) {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.FIELD_NAME_NOT_VALID);
            return;
        }
        DiagramView diagramView = ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView();
        diagramView.getCommandManager().addCommand(new EditConnectionCommand(k, tfName.getText(), (Cardinality) cbCardinality.getSelectedItem(), (Visibility) cbVisibility.getSelectedItem()));

    }
}
