package main.java.raf.dsw.classycraft.app.controller.sidebar.dic.dcc;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class DrawFieldAction extends AbstractClassyAction {
	public DrawFieldAction() {
		putValue(SMALL_ICON, loadIcon("/images/icons/f.png"));
		putValue(NAME, "draw field");
		putValue(SHORT_DESCRIPTION, "draw field");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getPackageView().startDrawFieldState();
	}
}
