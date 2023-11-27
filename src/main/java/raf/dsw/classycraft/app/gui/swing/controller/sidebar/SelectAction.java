package main.java.raf.dsw.classycraft.app.gui.swing.controller.sidebar;

import main.java.raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class SelectAction extends AbstractClassyAction{
	public SelectAction(){
		putValue(SMALL_ICON, loadIcon("/images/icons/draw_class.png"));
		putValue(NAME, "select");
		putValue(SHORT_DESCRIPTION, "select");
	}
	@Override
	public void actionPerformed(ActionEvent e){
		MainFrame.getInstance().getPackageView().getStateManager().setSelectState();
		
	}
}
