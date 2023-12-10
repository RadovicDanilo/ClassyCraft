package main.java.raf.dsw.classycraft.app.state.concrete.dic.dcc;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.ClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.EnumPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Field;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class DrawFieldState extends DrawClassContentState {
	@Override
	public void mousePressed(MouseEvent e, DiagramView diagramView) {
		for(ElementPainter elementPainter : diagramView.getElementPainters()) {
			if(!(elementPainter instanceof InterClassPainter) || !((InterClassPainter) elementPainter).getRectangle().contains(diagramView.adjustPoint(e.getPoint()))) {
				continue;
			}
			if(elementPainter instanceof ClassPainter) {
				String name = JOptionPane.showInputDialog("Field name:");
				String type = JOptionPane.showInputDialog("Field type:");
				Visibility visibility = (Visibility) JOptionPane.showInputDialog(null, "Visbili", "v", JOptionPane.QUESTION_MESSAGE, null, Visibility.values(), Visibility.PUBLIC);
				
				if(name != null && type != null && name.matches("[a-zA-Z]+") && type.matches("[a-zA-Z]+")) {
					((Klasa) elementPainter.getDiagramElement()).addField(new Field(name, visibility, type));
				}else {
					//TODO SYSTEM EVENT
				}
			}
			if(elementPainter instanceof EnumPainter) {
				String name = JOptionPane.showInputDialog("Enum name:");
				if(name != null && name.matches("[a-zA-Z]+")) {
					((Enum) elementPainter.getDiagramElement()).addEnum(name);
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
