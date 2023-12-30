package main.java.raf.dsw.classycraft.app.controller.sidebar.draw_interclass.draw_class_content;

import main.java.raf.dsw.classycraft.app.command.implementation.EditClassCommand;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.ClassContent;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Field;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Method;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UpdateClassAction {
    public UpdateClassAction(Klasa k,
                             JTextField tfName,
                             ArrayList<JComboBox<Visibility>> cbFieldVisibility,
                             ArrayList<JTextField> tfFieldValues,
                             ArrayList<JTextField> tfFieldNames,
                             ArrayList<JCheckBox> checkBoxesFields,
                             ArrayList<JComboBox<Visibility>> cbMethodVisibility,
                             ArrayList<JTextField> tfMethodValues,
                             ArrayList<JTextField> tfMethodNames,
                             ArrayList<JCheckBox> checkBoxesMethods) {
        ArrayList<ClassContent> contents = new ArrayList<>();
        for (int j = 0; j < tfFieldNames.size(); j++) {
            if (checkBoxesFields.get(j).isSelected()) {
                continue;
            }
            if (tfFieldNames.get(j).getText() == null || tfFieldNames.get(j).getText().length() == 0 ||
                    !tfFieldNames.get(j).getText().substring(0, 1).matches("[a-zA-Z]+") || !tfFieldNames.get(j).getText().matches("^([\\w+\\-/])+$")) {
                ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.FIELD_NAME_NOT_VALID);
                return;
            }
            if (tfFieldValues.get(j).getText() == null || tfFieldValues.get(j).getText().length() == 0 ||
                    !tfFieldValues.get(j).getText().substring(0, 1).matches("[a-zA-Z]+") || !tfFieldValues.get(j).getText().matches("^([\\w+\\-/])+$")) {
                ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.FIELD_VALUE_NOT_VALID);
                return;
            }
            contents.add(new Field(tfFieldNames.get(j).getText(), (Visibility) cbFieldVisibility.get(j).getSelectedItem(), tfFieldValues.get(j).getText()));
        }
        for (int j = 0; j < tfMethodNames.size(); j++) {
            if (checkBoxesMethods.get(j).isSelected()) {
                continue;
            }
            if (tfMethodNames.get(j).getText() == null || tfMethodNames.get(j).getText().length() == 0 ||
                    !tfMethodNames.get(j).getText().substring(0, 1).matches("[a-zA-Z]+") || !tfMethodNames.get(j).getText().matches("^([\\w+\\-/])+$")) {
                ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.METHOD_NAME_NOT_VALID);
                return;
            }
            if (tfMethodValues.get(j).getText() == null || tfMethodValues.get(j).getText().length() == 0 ||
                    !tfMethodValues.get(j).getText().substring(0, 1).matches("[a-zA-Z]+") || !tfMethodValues.get(j).getText().matches("^([\\w+\\-/])+$")) {
                ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.METHOD_RETURN_VALUE_NOT_VALID);
                return;
            }
            contents.add(new Method(tfMethodNames.get(j).getText(), (Visibility) cbMethodVisibility.get(j).getSelectedItem(), tfMethodValues.get(j).getText()));
        }
        Set<ClassContent> set = new HashSet<>(contents);
        if (set.size() != contents.size()) {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.DUPLICATE_FIELDS_OR_METHODS);
            return;
        }
        DiagramView diagramView = ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView();
        diagramView.getCommandManager().addCommand(new EditClassCommand(k, tfName.getText(), contents));

    }
}
