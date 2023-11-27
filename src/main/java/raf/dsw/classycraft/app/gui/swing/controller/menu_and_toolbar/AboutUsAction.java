package main.java.raf.dsw.classycraft.app.gui.swing.controller.menu_and_toolbar;

import main.java.raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.AboutUsFrame;

import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractClassyAction{
	
	public AboutUsAction(){
		putValue(SMALL_ICON, loadIcon("/images/icons/about.png"));
		putValue(NAME, "About us");
		putValue(SHORT_DESCRIPTION, "About us");
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		AboutUsFrame.getInstance().setVisible(true);
	}
}
