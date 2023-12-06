package main.java.raf.dsw.classycraft.app.controller.sidebar.dic;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class DrawClassAction extends AbstractClassyAction {
	public DrawClassAction() {
		putValue(SMALL_ICON, loadIcon("/images/icons/c.png"));
		putValue(NAME, "draw class");
		putValue(SHORT_DESCRIPTION, "draw class");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getPackageView().startDrawClassState();
		
	}
}
