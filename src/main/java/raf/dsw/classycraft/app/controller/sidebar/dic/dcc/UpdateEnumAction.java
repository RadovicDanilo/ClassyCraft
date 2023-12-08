package main.java.raf.dsw.classycraft.app.controller.sidebar.dic.dcc;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UpdateEnumAction extends AbstractClassyAction {
	public UpdateEnumAction(Enum e, JTextField name, ArrayList<JTextField> tfEnums) {
		e.setName(name.getText());
		ArrayList<String> content = new ArrayList<>();
		for(JTextField tf : tfEnums) {
			content.add(tf.getText());
		}
		Set<String> set = new HashSet<>(content);//proverava da li ima duplikata
		if(set.size() == content.size()) {
			e.setContents(content);
		}else {
			//TODO SYSTEM EVENT
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
	}
}
