package main.java.raf.dsw.classycraft.app.state.concrete.dic;

import main.java.raf.dsw.classycraft.app.gui.swing.view.painter.icp.EnumPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass.Interface;

import java.awt.event.MouseEvent;

public class DrawInterfaceState extends DrawInterClassState {
	@Override
	public void mouseClicked(MouseEvent e, DiagramView diagramView) {
		int i = 1;
		String name;
		while(true) {
			name = "interfejs " + i;
			boolean flag = true;
			for(ClassyNode classyNode : diagramView.getDiagram().getChildren()) {
				if(classyNode.getName().equals(name)) {
					flag = false;
					break;
				}
			}
			if(flag)
				break;
			i++;
		}
		Interface interfejs = new Interface(diagramView.getDiagram(), name, Visibility.PUBLIC);
		diagramView.getElementPainters().add(new EnumPainter(e.getPoint()));
		diagramView.getDiagram().addChild(interfejs);
	}
	
	@Override
	public void mouseDragged(MouseEvent e, DiagramView diagramView) {
	
	}
	
	@Override
	public void mouseRelease(MouseEvent e, DiagramView diagramView) {
	
	}
}
