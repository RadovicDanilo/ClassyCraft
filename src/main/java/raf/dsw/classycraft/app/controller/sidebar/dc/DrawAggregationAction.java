package main.java.raf.dsw.classycraft.app.controller.sidebar.dc;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class DrawAggregationAction extends AbstractClassyAction {
	public DrawAggregationAction() {
		putValue(SMALL_ICON, loadIcon("/images/icons/agr.png"));
		putValue(NAME, "draw aggregation");
		putValue(SHORT_DESCRIPTION, "draw aggregation");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getPackageView().startDrawAggregationState();
	}
}
