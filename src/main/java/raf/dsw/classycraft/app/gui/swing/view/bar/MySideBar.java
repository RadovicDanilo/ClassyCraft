package main.java.raf.dsw.classycraft.app.gui.swing.view.bar;

import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import javax.swing.*;

public class MySideBar extends JToolBar {
	public MySideBar() {
		super(VERTICAL);
		setFloatable(false);
		add(MainFrame.getInstance().getActionManager().getSelectAction());
		add(MainFrame.getInstance().getActionManager().getMultiSelectStateAction());
		add(MainFrame.getInstance().getActionManager().getRemoveAction());
		
		add(MainFrame.getInstance().getActionManager().getZoomToFitAction());
		
		add(MainFrame.getInstance().getActionManager().getDuplicateAction());
		
		add(MainFrame.getInstance().getActionManager().getDrawClassAction());
		add(MainFrame.getInstance().getActionManager().getDrawInterfaceAction());
		add(MainFrame.getInstance().getActionManager().getDrawEnumAction());
		
		add(MainFrame.getInstance().getActionManager().getDrawAggregationAction());
		add(MainFrame.getInstance().getActionManager().getDrawCompositionAction());
		add(MainFrame.getInstance().getActionManager().getDrawDependencyAction());
		add(MainFrame.getInstance().getActionManager().getDrawGeneralisationAction());
		
		add(MainFrame.getInstance().getActionManager().getDrawFieldAction());
		add(MainFrame.getInstance().getActionManager().getDrawMethodAction());
		add(MainFrame.getInstance().getActionManager().getEditContentAction());
		
		
	}
}
