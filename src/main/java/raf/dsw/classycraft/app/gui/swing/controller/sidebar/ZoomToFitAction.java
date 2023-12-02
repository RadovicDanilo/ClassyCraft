package main.java.raf.dsw.classycraft.app.gui.swing.controller.sidebar;

import main.java.raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomToFitAction extends AbstractClassyAction {
	public ZoomToFitAction() {
		putValue(SMALL_ICON, loadIcon("/images/icons/zoom_out.png"));//TODO icon
		putValue(NAME, "zoom out");
		putValue(SHORT_DESCRIPTION, "zoom out");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getPackageView().getStateManager().setZoomToFitState();
		
	}
}
