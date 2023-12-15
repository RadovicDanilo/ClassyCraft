package main.java.raf.dsw.classycraft.app.controller.sidebar.dic.dcc;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Aggregation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Cardinality;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Composition;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

import javax.swing.*;

public class UpdateAggregationAction {
	public UpdateAggregationAction(Connection k, JTextField tfName, JComboBox<Visibility> cbVisibility, JComboBox<Cardinality> cbCardinality) {
		if(tfName.getText() == null || tfName.getText().length() == 0 || !tfName.getText().substring(0, 1).matches("[a-zA-Z]+") || !tfName.getText().matches("^([\\w+\\-/])+$")) {
			ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.FIELD_NAME_NOT_VALID);
			return;
		}
		if(k instanceof Aggregation) {
			((Aggregation) k).setFieldName(tfName.getText());
			((Aggregation) k).setCardinality((Cardinality) cbCardinality.getSelectedItem());
			((Aggregation) k).setVisibility((Visibility) cbVisibility.getSelectedItem());
		}
		if(k instanceof Composition) {
			((Composition) k).setFieldName(tfName.getText());
			((Composition) k).setCardinality((Cardinality) cbCardinality.getSelectedItem());
			((Composition) k).setVisibility((Visibility) cbVisibility.getSelectedItem());
		}
		
	}
}
