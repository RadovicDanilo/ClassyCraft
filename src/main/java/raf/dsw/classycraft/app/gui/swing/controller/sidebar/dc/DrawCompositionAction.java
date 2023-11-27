package main.java.raf.dsw.classycraft.app.gui.swing.controller.sidebar.dc;

import main.java.raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class DrawCompositionAction extends AbstractClassyAction{
	public DrawCompositionAction(){
		putValue(SMALL_ICON, loadIcon("/images/icons/draw_class.png"));
		putValue(NAME, "draw composition");
		putValue(SHORT_DESCRIPTION, "draw composition");
	}
	@Override
	public void actionPerformed(ActionEvent e){
		MainFrame.getInstance().getPackageView().getStateManager().setDrawCompositionState();
		
	}
}
