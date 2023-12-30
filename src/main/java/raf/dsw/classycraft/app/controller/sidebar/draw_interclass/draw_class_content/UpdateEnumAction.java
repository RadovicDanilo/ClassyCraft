package main.java.raf.dsw.classycraft.app.controller.sidebar.draw_interclass.draw_class_content;

import main.java.raf.dsw.classycraft.app.command.implementation.EditEnumCommand;
import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UpdateEnumAction extends AbstractClassyAction {
    public UpdateEnumAction(Enum e,
                            JTextField tfName,
                            ArrayList<JTextField> tfEnums,
                            ArrayList<JCheckBox> checkBoxes) {

        ArrayList<String> content = new ArrayList<>();
        for (int i = 0; i < tfEnums.size(); i++) {
            JTextField tf = tfEnums.get(i);
            if (checkBoxes.get(i).isSelected()) {
                continue;
            }
            if (tf.getText() == null || tf.getText().length() == 0 || !tf.getText().substring(0, 1).matches("[a-zA-Z]+") || !tf.getText().matches("^([\\w+\\-/])+$")) {
                ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.ENUM_NOT_VALID);
                return;
            }
            content.add(tf.getText());

        }
        Set<String> set = new HashSet<>(content);
        if (set.size() != content.size()) {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.DUPLICATE_FIELDS_OR_METHODS);
            return;
        }

        DiagramView diagramView = ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView();
        diagramView.getCommandManager().addCommand(new EditEnumCommand(e, tfName.getText(), content));


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
