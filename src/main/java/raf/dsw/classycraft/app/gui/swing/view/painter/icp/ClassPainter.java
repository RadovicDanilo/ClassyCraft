package main.java.raf.dsw.classycraft.app.gui.swing.view.painter.icp;

import main.java.raf.dsw.classycraft.app.gui.swing.view.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass.Klasa;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ClassPainter extends ElementPainter {
	
	public ClassPainter(Point starPoint) {
		super(starPoint);
	}
	@Override
	public void draw(Graphics2D g) {
		g.setBackground(Color.BLACK);
		g.drawRect(super.getStarPoint().x, super.getStarPoint().y, 100, 400);
	}
	

}
