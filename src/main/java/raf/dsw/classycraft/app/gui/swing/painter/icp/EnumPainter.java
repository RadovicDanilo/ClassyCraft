package main.java.raf.dsw.classycraft.app.gui.swing.painter.icp;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;

import java.awt.*;

public class EnumPainter extends InterClassPainter {
	
	public EnumPainter(DiagramElement diagramElement) {
		super(diagramElement);
	}
	
	@Override
	public void addElement(DiagramElement element) {
		super.addElement(element);
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
		int height = (2 + ((Enum) getDiagramElement()).getContents().size()) * (g.getFontMetrics().getHeight() + 2);
		height += 2;
		
		int width = 0;
		width = Math.max(g.getFontMetrics().stringWidth("<<Enumeration>>"), width);
		width = Math.max(g.getFontMetrics().stringWidth(getDiagramElement().getName()), width);
		for(String e : ((Enum) getDiagramElement()).getContents()) {
			width = Math.max(g.getFontMetrics().stringWidth(e), width);
		}
		width += 4;
		
		
		getDiagramElement().setCurrentHeight(height);
		getDiagramElement().setCurrentWidth(width);
		
		boolean flag = true;
		Rectangle r = this.getRectangle();
		
		do {
			flag = true;
			for(ElementPainter dp : ((DiagramView) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getElementPainters()) {
				if(dp instanceof InterClassPainter && !dp.equals(this) && ((InterClassPainter) dp).intersects(r)) {
					((InterClassPainter) dp).getDiagramElement().setX(getDiagramElement().getX() + width + 1);
					((InterClassPainter) dp).getDiagramElement().setY(getDiagramElement().getY() + height + 1);
					flag = false;
					break;
				}
			}
		}while(!flag);
		
		g.drawRect(getDiagramElement().getX(), getDiagramElement().getY(), width, height);
		
		int yOffset = g.getFontMetrics().getHeight() + 2;
		int xOffset;
		
		xOffset = (width - g.getFontMetrics().stringWidth("<<Enumeration>>")) / 2;
		g.drawString("<<Enumeration>>", getDiagramElement().getX() + xOffset, getDiagramElement().getY() + yOffset);
		
		int i = 2;
		xOffset = (width - g.getFontMetrics().stringWidth(getDiagramElement().getName())) / 2;
		g.drawString(getDiagramElement().getName(), getDiagramElement().getX() + xOffset, getDiagramElement().getY() + yOffset * i);
		
		for(String e : ((Enum) getDiagramElement()).getContents()) {
			i++;
			xOffset = (width - g.getFontMetrics().stringWidth(e)) / 2;
			g.drawString(e, getDiagramElement().getX() + 2, getDiagramElement().getY() + yOffset * i);
		}
	
		
	}
}
