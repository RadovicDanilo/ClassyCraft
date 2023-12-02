package main.java.raf.dsw.classycraft.app.state.concrete.dic;

import main.java.raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.painter.icp.ClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass.Klasa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class DrawClassState extends DrawInterClassState {
	
	@Override
	public void mouseClicked(MouseEvent e, DiagramView diagramView) {
		int i = 1;
		String name;
		while(true) {
			name = "klasa " + i;
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
		Klasa klasa = new Klasa(diagramView.getDiagram(), name, Visibility.PUBLIC);
		diagramView.getElementPainters().add(new ClassPainter(e.getPoint(),klasa));
		diagramView.getDiagram().addChild(klasa);
		ClassyTreeItem parent = MainFrame.getInstance().getClassyTree().getChild(diagramView.getDiagram());
		MainFrame.getInstance().getClassyTree().addChild(parent, klasa);
		
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e, DiagramView diagramView) {
	
	}
	
	@Override
	public void mouseRelease(MouseEvent e, DiagramView diagramView) {
	
	}
}
