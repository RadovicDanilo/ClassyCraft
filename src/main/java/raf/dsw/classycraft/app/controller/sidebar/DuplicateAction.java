package main.java.raf.dsw.classycraft.app.controller.sidebar;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class DuplicateAction extends AbstractClassyAction {
	public DuplicateAction() {
		putValue(SMALL_ICON, loadIcon("/images/icons/duplicate.png"));
		putValue(NAME, "duplicate");
		putValue(SHORT_DESCRIPTION, "duplicate");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().getPackageView().startDuplicateAction();
	}
}
