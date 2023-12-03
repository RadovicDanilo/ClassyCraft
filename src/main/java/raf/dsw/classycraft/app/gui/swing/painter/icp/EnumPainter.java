package main.java.raf.dsw.classycraft.app.gui.swing.painter.icp;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;

import java.awt.*;

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
		
		
		setCurrentHeight(height);
		setCurrentWidth(width);
		
		boolean flag = true;
		Rectangle r = this.getRectangle();
		
		do {//TODO popraviti ovu glupost
			flag = true;
			for(ElementPainter dp : ((DiagramView) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getElementPainters()) {
				if(dp instanceof InterClassPainter && !dp.equals(this) && ((InterClassPainter) dp).intersects(r)) {
					((InterClassPainter) dp).setX(getX() + width + 10);
					((InterClassPainter) dp).setY(getY() + height + 10);
					flag = false;
					break;
				}
			}
		}while(!flag);
		
		g.drawRect(getX(), getY(), width, height);
		
		int yOffset = g.getFontMetrics().getHeight() + 2;
		int xOffset;
		
		xOffset = (width - g.getFontMetrics().stringWidth("<<Enumeration>>")) / 2;
		g.drawString("<<Enumeration>>", getX() + xOffset, getY() + yOffset);
		
		int i = 2;
		xOffset = (width - g.getFontMetrics().stringWidth(getDiagramElement().getName())) / 2;
		g.drawString(getDiagramElement().getName(), getX() + xOffset, getY() + yOffset * i);
		System.out.println(height);
		System.out.println(width);
		for(String e : ((Enum) getDiagramElement()).getContents()) {
			i++;
			xOffset = (width - g.getFontMetrics().stringWidth(e)) / 2;
			g.drawString(e, getX() + 2, getY() + yOffset * i);
		}
		System.out.println("E");
		System.out.println(height);
		System.out.println(width);
		
	}
}
