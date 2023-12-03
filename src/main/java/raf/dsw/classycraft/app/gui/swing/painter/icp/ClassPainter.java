package main.java.raf.dsw.classycraft.app.gui.swing.painter.icp;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.InterClassPainter;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.ClassContent;

import java.awt.*;

public class ClassPainter extends InterClassPainter {
	
	
	public ClassPainter(DiagramElement diagramElement, int x, int y) {
		super(diagramElement, x, y);
	}
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		
		int height = (2 + ((Klasa) getDiagramElement()).getContents().size()) * (g.getFontMetrics().getHeight() + 2);
		height += 2;
		
		int width = 0;
		width = Math.max(g.getFontMetrics().stringWidth("<<Class>>"), width);
		width = Math.max(g.getFontMetrics().stringWidth(getDiagramElement().getName()), width);
		for(ClassContent c : ((Klasa) getDiagramElement()).getContents()) {
			width = Math.max(g.getFontMetrics().stringWidth(c.toString()), width);
		}
		width += 4;
		
		setCurrentHeight(height);
		setCurrentWidth(width);
		
		g.drawRect(getX(), getY(), width, height);
		
		int yOffset = g.getFontMetrics().getHeight() + 2;
		int xOffset;
		
		xOffset = (width - g.getFontMetrics().stringWidth("<<Class>>")) / 2;
		g.drawString("<<Class>>", getX() + xOffset, getY() + yOffset);
		
		int i = 2;
		xOffset = (width - g.getFontMetrics().stringWidth(getDiagramElement().getName())) / 2;
		g.drawString(getDiagramElement().getName(), getX() + xOffset, getY() + yOffset * i);
		
		for(ClassContent c : ((Klasa) getDiagramElement()).getContents()) {
			i++;
			xOffset = (width - g.getFontMetrics().stringWidth(c.toString())) / 2;
			g.drawString(c.toString(), getX()+2, getY() + yOffset * i);
		}
	}
}
