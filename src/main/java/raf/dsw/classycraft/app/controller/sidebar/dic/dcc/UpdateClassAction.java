package main.java.raf.dsw.classycraft.app.controller.sidebar.dic.dcc;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
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
	public UpdateClassAction(Klasa k, JTextField tfName,
	                         ArrayList<JComboBox<Visibility>> cbFieldVisibility, ArrayList<JTextField> tfFieldValues, ArrayList<JTextField> tfFieldNames, ArrayList<JCheckBox> checkBoxesFields,
	                         ArrayList<JComboBox<Visibility>> cbMethodVisibility, ArrayList<JTextField> tfMethodValues, ArrayList<JTextField> tfMethodNames, ArrayList<JCheckBox> checkBoxesMethods) {
		k.setName(tfName.getText());
		ArrayList<ClassContent> contents = new ArrayList<>();
		for(int j = 0; j < tfFieldNames.size(); j++) {
			if(checkBoxesFields.get(j).isSelected()) {
				continue;
			}
			contents.add(new Field(tfFieldNames.get(j).getText(), (Visibility) cbFieldVisibility.get(j).getSelectedItem(), tfFieldValues.get(j).getText()));
		}
		for(int j = 0; j < tfMethodNames.size(); j++) {
			if(checkBoxesMethods.get(j).isSelected()) {
				continue;
			}
			contents.add(new Method(tfMethodNames.get(j).getText(), (Visibility) cbMethodVisibility.get(j).getSelectedItem(), tfMethodValues.get(j).getText()));
		}
		Set<ClassContent> set = new HashSet<>(contents);
		if(set.size() == contents.size()) {
			k.setContents(contents);
		}else {
			ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.DUPLICATE_FIELDS_OR_METHODS);
		}
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getClassyTree().getTreeView());
		
	}
}
