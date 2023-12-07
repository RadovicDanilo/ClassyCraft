package main.java.raf.dsw.classycraft.app.gui.swing.painter.icp;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.ClassContent;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Method;

import java.awt.*;

public class InterfacePainter extends InterClassPainter {
	
	public InterfacePainter(DiagramElement diagramElement) {
		super(diagramElement);
	}
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		if(((DiagramView)MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getSelected().contains(this)){
			g.setColor(Color.RED);
			g.setStroke(strokeDashed);
		}else{
			g.setColor(Color.BLACK);
			g.setStroke(normalStroke);
		}
		int height = (2 + ((Interface) getDiagramElement()).getMethods().size()) * (g.getFontMetrics().getHeight() + 2);
		height += 2;
		
		int width = 0;
		width = Math.max(g.getFontMetrics().stringWidth("<<Interface>>"), width);
		width = Math.max(g.getFontMetrics().stringWidth(getDiagramElement().getName()), width);
		for(Method c : ((Interface) getDiagramElement()).getMethods()) {
			width = Math.max(g.getFontMetrics().stringWidth(c.toString()), width);
		}
		width += 4;
		
		getDiagramElement().setCurrentHeight(height);
		getDiagramElement().setCurrentWidth(width);
		
		boolean flag;
		Rectangle r = this.getRectangle();
		
		do {
			flag = true;
			for(ElementPainter dp : ((DiagramView) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getElementPainters()) {
				if(dp instanceof InterClassPainter && !dp.equals(this) && ((InterClassPainter) dp).intersects(r)) {
					((InterClassPainter) dp).getDiagramElement().setX(getDiagramElement().getX() + width );
					((InterClassPainter) dp).getDiagramElement().setY(getDiagramElement().getY() + height );
					flag = false;
					break;
				}
			}
		}while(!flag);
		
		g.drawRect(getDiagramElement().getX(), getDiagramElement().getY(), width, height);
		
		int yOffset = g.getFontMetrics().getHeight() + 2;
		int xOffset;
		
		xOffset = (width - g.getFontMetrics().stringWidth("<<Interface>>")) / 2;
		g.drawString("<<Interface>>", getDiagramElement().getX() + xOffset, getDiagramElement().getY() + yOffset);
		
		int i = 2;
		xOffset = (width - g.getFontMetrics().stringWidth(getDiagramElement().getName())) / 2;
		g.drawString(getDiagramElement().getName(), getDiagramElement().getX() + xOffset, getDiagramElement().getY() + yOffset * i);
		
		for(ClassContent c : ((Interface) getDiagramElement()).getMethods()) {
			i++;
			xOffset = (width - g.getFontMetrics().stringWidth(c.toString())) / 2;
			g.drawString(c.toString(), getDiagramElement().getX() + 2, getDiagramElement().getY() + yOffset * i);
		}
	}
}
