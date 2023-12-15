package main.java.raf.dsw.classycraft.app.controller.sidebar.dic.dcc;

import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Aggregation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Cardinality;

import javax.swing.*;

public class UpdateAggregationAction {
    public UpdateAggregationAction(Aggregation k, JTextField tfName, JComboBox<Visibility> cbVisibility, JComboBox<Cardinality> cbCardinality) {
            k.setName(tfName.getName());
            k.setCardinality((Cardinality) cbCardinality.getSelectedItem());
            k.setVisibility((Visibility) cbVisibility.getSelectedItem());
    }
}
