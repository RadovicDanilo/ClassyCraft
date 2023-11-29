package main.java.raf.dsw.classycraft.app.gui.swing.view.painter.icp;

import main.java.raf.dsw.classycraft.app.gui.swing.view.painter.ElementPainter;

import java.awt.*;

public class EnumPainter extends ElementPainter {
	public EnumPainter(Point starPoint) {
		super(starPoint);
	}
	@Override
	public void draw(Graphics2D g) {
		g.setBackground(Color.BLACK);
		g.drawRect(super.getStarPoint().x, super.getStarPoint().y, 100, 200);
	}
}
