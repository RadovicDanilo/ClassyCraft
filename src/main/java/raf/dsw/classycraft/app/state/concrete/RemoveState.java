package main.java.raf.dsw.classycraft.app.state.concrete;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.cp.ConnectionPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.state.State;
import main.java.raf.dsw.classycraft.app.state.StateImplement;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class RemoveState extends StateImplement implements State {
	@Override
	public void mousePressed(MouseEvent e, DiagramView diagramView) {
		ElementPainter removedElement = null;
		for(ElementPainter elementPainter : diagramView.getElementPainters()) {
			if(elementPainter.contains(diagramView.correctMouseX(e.getX()), diagramView.correctMouseY(e.getY()))) {
				diagramView.getElementPainters().remove(elementPainter);
				MainFrame.getInstance().getClassyTree().removeNode(new ClassyTreeItem(elementPainter.getDiagramElement()));
				removedElement = elementPainter;
				break;
			}
		}
		if(removedElement instanceof InterClassPainter) {
			for(int i = 0; i < diagramView.getElementPainters().size(); i++) {
				ElementPainter elementPainter = diagramView.getElementPainters().get(i);
				if(elementPainter instanceof ConnectionPainter && (((ConnectionPainter) elementPainter).getDiagramElement().getFrom() == removedElement.getDiagramElement() || ((ConnectionPainter) elementPainter).getDiagramElement().getTo() == removedElement.getDiagramElement())) {
					MainFrame.getInstance().getClassyTree().removeNode(new ClassyTreeItem(elementPainter.getDiagramElement()));
					diagramView.getElementPainters().remove(elementPainter);
					i--;
				}
			}
		}
		diagramView.setSelected(new ArrayList<>());
	}
	
	@Override
	public void mouseDragged(MouseEvent e, DiagramView diagramView) {
	
	}
	
	@Override
	public void mouseRelease(MouseEvent e, DiagramView diagramView) {
	
	}
}
