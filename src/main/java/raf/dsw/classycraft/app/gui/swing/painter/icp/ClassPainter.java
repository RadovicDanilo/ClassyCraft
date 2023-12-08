package main.java.raf.dsw.classycraft.app.gui.swing.painter.icp;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.ClassContent;

import java.awt.*;

public class ClassPainter extends InterClassPainter {
	
	public ClassPainter(Klasa diagramElement) {
		super(diagramElement);
	}
	
	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		if(((DiagramView) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getSelected().contains(this)) {
			g.setColor(Color.RED);
			g.setStroke(strokeDashed);
		}else {
			g.setColor(Color.BLACK);
			g.setStroke(normalStroke);
		}
		
		int height = (2 + ((Klasa) getDiagramElement()).getContents().size()) * (g.getFontMetrics().getHeight() + 2);
		height += 2;
		
		int width = 0;
		width = Math.max(g.getFontMetrics().stringWidth("<<Class>>"), width);
		width = Math.max(g.getFontMetrics().stringWidth(getDiagramElement().getName()), width);
		for(ClassContent c : ((Klasa) getDiagramElement()).getContents()) {
			width = Math.max(g.getFontMetrics().stringWidth(c.toString()), width);
		}
		width += 4;
		
		getDiagramElement().setCurrentHeight(height);
		getDiagramElement().setCurrentWidth(width);
		
		boolean flag;
		Rectangle r = this.getRectangle();
		
		do {
			flag = false;
			for(ElementPainter dp : ((DiagramView) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getElementPainters()) {
				if(dp instanceof InterClassPainter && !dp.equals(this) && ((InterClassPainter) dp).intersects(r)) {
					((InterClassPainter) dp).getDiagramElement().setX(((InterClassPainter) dp).getDiagramElement().getX() + width + 1);
					((InterClassPainter) dp).getDiagramElement().setY(((InterClassPainter) dp).getDiagramElement().getY() + height + 1);
					flag = true;
					break;
				}
			}
		}while(flag);
		
		g.drawRect(getDiagramElement().getX(), getDiagramElement().getY(), width, height);
		
		int yOffset = g.getFontMetrics().getHeight() + 2;
		int xOffset;
		
		xOffset = (width - g.getFontMetrics().stringWidth("<<Class>>")) / 2;
		g.drawString("<<Class>>", getDiagramElement().getX() + xOffset, getDiagramElement().getY() + yOffset);
		
		int i = 2;
		xOffset = (width - g.getFontMetrics().stringWidth(getDiagramElement().getName())) / 2;
		g.drawString(getDiagramElement().getName(), getDiagramElement().getX() + xOffset, getDiagramElement().getY() + yOffset * i);
		
		for(ClassContent c : ((Klasa) getDiagramElement()).getContents()) {
			i++;
			xOffset = (width - g.getFontMetrics().stringWidth(c.toString())) / 2;
			g.drawString(c.toString(), getDiagramElement().getX() + 2, getDiagramElement().getY() + yOffset * i);
		}
	}
}
