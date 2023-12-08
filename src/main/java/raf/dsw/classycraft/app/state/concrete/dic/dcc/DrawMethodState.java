package main.java.raf.dsw.classycraft.app.state.concrete.dic.dcc;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.ClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterfacePainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Method;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class DrawMethodState extends DrawClassContentState {
	
	@Override
	public void mousePressed(MouseEvent e, DiagramView diagramView) {
		for(ElementPainter elementPainter : ((DiagramView) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getElementPainters()) {
			if(!(elementPainter instanceof InterClassPainter) || !((InterClassPainter) elementPainter).getRectangle().contains(e.getPoint())) {
				continue;
			}
			if(elementPainter instanceof ClassPainter) {
				String name = JOptionPane.showInputDialog("Method name:");
				String returnValue = JOptionPane.showInputDialog("return value:");
				Visibility visibility = (Visibility) JOptionPane.showInputDialog(null, "Visbili", "v", JOptionPane.QUESTION_MESSAGE, null, Visibility.values(), Visibility.PUBLIC);
				if(name != null && returnValue != null && name.matches("[a-zA-Z]+") && returnValue.matches("[a-zA-Z]+")) {
					((Klasa) elementPainter.getDiagramElement()).addMethod(new Method(name, visibility, returnValue));
				}else {
					//TODO SYSTEM EVENT
				}
			}
			if(elementPainter instanceof InterfacePainter) {
				String name = JOptionPane.showInputDialog("Method name:");
				String returnValue = JOptionPane.showInputDialog("return value:");
				Visibility visibility = (Visibility) JOptionPane.showInputDialog(null, "Visbili", "v", JOptionPane.QUESTION_MESSAGE, null, Visibility.values(), Visibility.PUBLIC);
				if(name != null && returnValue != null && name.matches("[a-zA-Z]+") && returnValue.matches("[a-zA-Z]+")) {
					((Interface) elementPainter.getDiagramElement()).addMethod(new Method(name, visibility, returnValue));
				}else {
					//TODO SYSTEM EVENT
				}
				
			}
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e, DiagramView diagramView) {
	
	}
	
	@Override
	public void mouseRelease(MouseEvent e, DiagramView diagramView) {
	
	}
}
