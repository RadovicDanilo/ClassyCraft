package main.java.raf.dsw.classycraft.app.controller.sidebar.dic.dcc;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UpdateEnumAction extends AbstractClassyAction {
	public UpdateEnumAction(Enum e, JTextField name, ArrayList<JTextField> tfEnums, ArrayList<JCheckBox> checkBoxes) {
		e.setName(name.getText());
		ArrayList<String> content = new ArrayList<>();
		for(int i = 0; i < tfEnums.size(); i++) {
			JTextField tf = tfEnums.get(i);
			if(checkBoxes.get(i).isSelected()) {
				continue;
			}
			content.add(tf.getText());
		}
		Set<String> set = new HashSet<>(content);//proverava da li ima duplikata
		if(set.size() == content.size()) {
			e.setContents(content);
		}else {
			//TODO SYSTEM EVENT
		}
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getClassyTree().getTreeView());
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
	}
}
