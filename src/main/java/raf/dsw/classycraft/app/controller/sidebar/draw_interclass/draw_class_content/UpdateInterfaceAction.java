package main.java.raf.dsw.classycraft.app.controller.sidebar.draw_interclass.draw_class_content;

import main.java.raf.dsw.classycraft.app.command.implementation.EditInterfaceCommand;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Method;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UpdateInterfaceAction {
    public UpdateInterfaceAction(Interface i, JTextField tfName, ArrayList<JComboBox<Visibility>> cbMethodVisibility, ArrayList<JTextField> tfMethodValues, ArrayList<JTextField> tfMethodNames, ArrayList<JCheckBox> checkBoxes) {
        ArrayList<Method> methods = new ArrayList<>();
        for (int j = 0; j < tfMethodNames.size(); j++) {
            if (checkBoxes.get(j).isSelected()) {
                continue;
            }
            methods.add(new Method(tfMethodNames.get(j).getText(), (Visibility) cbMethodVisibility.get(j).getSelectedItem(), tfMethodValues.get(j).getText()));
        }

        Set<Method> set = new HashSet<>(methods);

        if (set.size() != methods.size()) {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.DUPLICATE_FIELDS_OR_METHODS);
            return;
        }
        DiagramView diagramView = ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView();
        diagramView.getCommandManager().addCommand(new EditInterfaceCommand(i, tfName.getText(), methods));

    }
}
