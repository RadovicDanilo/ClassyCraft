package main.java.raf.dsw.classycraft.app.gui.swing.tree.view;


import main.java.raf.dsw.classycraft.app.gui.swing.tree.controller.ClassyTreeCellEditor;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.controller.ClassyTreeOpenTabsListener;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.controller.ClassyTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class ClassyTreeView extends JTree {
	public ClassyTreeView(DefaultTreeModel defaultTreeModel) {
		setModel(defaultTreeModel);
		ClassyTreeCellRenderer classyTreeCellRenderer = new ClassyTreeCellRenderer();
		setCellRenderer(classyTreeCellRenderer);
		setCellEditor(new ClassyTreeCellEditor(this, classyTreeCellRenderer));
		addTreeSelectionListener(new ClassyTreeSelectionListener());
		addMouseListener(new ClassyTreeOpenTabsListener());
		setEditable(true);
	}
}
