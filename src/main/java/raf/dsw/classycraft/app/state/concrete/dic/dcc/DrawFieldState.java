package main.java.raf.dsw.classycraft.app.state.concrete.dic.dcc;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.ClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.EnumPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Field;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class DrawFieldState extends DrawClassContentState {
	@Override
	public void mouseClicked(MouseEvent e, DiagramView diagramView) {
		for(ElementPainter elementPainter : ((DiagramView) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getElementPainters()) {
			if(elementPainter instanceof ClassPainter) {
				if(((InterClassPainter) elementPainter).getRectangle().contains(e.getPoint())) {
					String name = JOptionPane.showInputDialog("Field name:");
					String type = JOptionPane.showInputDialog("Field type:");
					Visibility visibility = (Visibility) JOptionPane.showInputDialog(null, "Visbili", "v", JOptionPane.QUESTION_MESSAGE, null, Visibility.values(), Visibility.PUBLIC);
					((Klasa) elementPainter.getDiagramElement()).addField(new Field(name, visibility, type));
				}
			}
			if(elementPainter instanceof EnumPainter) {
				if(((InterClassPainter) elementPainter).getRectangle().contains(e.getPoint())) {
					String name = JOptionPane.showInputDialog("Enum name:");
					((Enum) elementPainter.getDiagramElement()).addEnum(name);
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
