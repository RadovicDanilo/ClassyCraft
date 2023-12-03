package main.java.raf.dsw.classycraft.app.gui.swing.painter.icp;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class EnumPainter extends InterClassPainter {
	
	public EnumPainter(DiagramElement diagramElement, int x, int y) {
		super(diagramElement, x, y);
	}
	
	@Override
	public void addElement(DiagramElement element) {
		super.addElement(element);
	}
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		
		int height = (2 + ((Enum) getDiagramElement()).getContents().size()) * (g.getFontMetrics().getHeight() + 2);
		height += 2;
		
		int width = 0;
		width = Math.max(g.getFontMetrics().stringWidth("<<Enumeration>>"), width);
		width = Math.max(g.getFontMetrics().stringWidth(getDiagramElement().getName()), width);
		for(String e : ((Enum) getDiagramElement()).getContents()) {
			width = Math.max(g.getFontMetrics().stringWidth(e), width);
		}
		width += 4;
		
		for(ElementPainter de : MainFrame.getInstance().getCurrentDiagramView().getElementPainters()) {
			if(de == this) {
				break;
			}
			if(de instanceof InterClassPainter) {
				int x1 = getX();
				int y1 = getY();
				
				int x2 = x1 + width;
				int y2 = y1 + height;
				
				int x3 = ((InterClassPainter) de).getX();
				int y3 = ((InterClassPainter) de).getY();
				
				int x4 = x3 + de.getCurrentWidth();
				int y4 = y3 + de.getCurrentHeight();
				
				if(!(x2 < x3 || y2 < y3 || x1 > x4 || y1 > y4)){
					MainFrame.getInstance().getCurrentDiagramView().getElementPainters().remove(this);
					return;
				}
			}
			
		}
		
		g.drawRect(getX(), getY(), width, height);
		
		int yOffset = g.getFontMetrics().getHeight() + 2;
		int xOffset;
		
		xOffset = (width - g.getFontMetrics().stringWidth("<<Enumeration>>")) / 2;
		g.drawString("<<Enumeration>>", getX() + xOffset, getY() + yOffset);
		
		int i = 2;
		xOffset = (width - g.getFontMetrics().stringWidth(getDiagramElement().getName())) / 2;
		g.drawString(getDiagramElement().getName(), getX() + xOffset, getY() + yOffset * i);
		
		for(String e : ((Enum) getDiagramElement()).getContents()) {
			i++;
			xOffset = (width - g.getFontMetrics().stringWidth(e)) / 2;
			g.drawString(e, getX() + xOffset, getY() + yOffset * i);
		}
		
	}
}
