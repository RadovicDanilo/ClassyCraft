package main.java.raf.dsw.classycraft.app.controller.sidebar.dic.dcc;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
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
		i.setName(tfName.getText());
		ArrayList<Method> methods = new ArrayList<>();
		for(int j = 0; j < tfMethodNames.size(); j++) {
			if(checkBoxes.get(j).isSelected()) {
				continue;
			}
			methods.add(new Method(tfMethodNames.get(j).getText(), (Visibility) cbMethodVisibility.get(j).getSelectedItem(), tfMethodValues.get(j).getText()));
		}
		
		Set<Method> set = new HashSet<>(methods);
		
		if(set.size() == methods.size()) {
			i.setMethods(methods);
		}else {
			ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.DUPLICATE_FIELDS_OR_METHODS);
		}
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getClassyTree().getTreeView());
		
	}
}
