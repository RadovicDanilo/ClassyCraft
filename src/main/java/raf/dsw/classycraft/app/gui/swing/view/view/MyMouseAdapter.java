package main.java.raf.dsw.classycraft.app.gui.swing.view.view;

import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseAdapter extends MouseAdapter {
	private DiagramView diagramView;
	
	public MyMouseAdapter(DiagramView diagramView) {
		this.diagramView = diagramView;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		MainFrame.getInstance().getPackageView().mouseClicked(e, diagramView);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		MainFrame.getInstance().getPackageView().mouseRelease(e, diagramView);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		MainFrame.getInstance().getPackageView().mouseDragged(e, diagramView);
	}
}
