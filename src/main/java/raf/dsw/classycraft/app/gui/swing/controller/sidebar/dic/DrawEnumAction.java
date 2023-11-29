package main.java.raf.dsw.classycraft.app.gui.swing.controller.sidebar.dic;

import main.java.raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class DrawEnumAction extends AbstractClassyAction {
	public DrawEnumAction() {
		putValue(SMALL_ICON, loadIcon("/images/icons/e.png"));
		putValue(NAME, "draw enum");
		putValue(SHORT_DESCRIPTION, "draw enum");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getPackageView().getStateManager().setDrawEnumState();
		
	}
}
