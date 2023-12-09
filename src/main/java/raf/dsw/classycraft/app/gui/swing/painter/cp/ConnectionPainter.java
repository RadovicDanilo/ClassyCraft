package main.java.raf.dsw.classycraft.app.gui.swing.painter.cp;

import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.icp.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public abstract class ConnectionPainter extends ElementPainter {


//	kompozicija ima pun romb na njenom početku a
//	agregacija prazan romb
//	(ne važi više ono za boje što sam rekao na času da ćemo možda dozvoliti...)
//	Takođe, kada se nacrta veza asocijacije, ne treba da se prikaže novonastalo polje u jednom od entiteta
//	(ljudi koji čitaju dijagram znaju šta veze znače i da to tako treba da se protumači).
	
	public ConnectionPainter(Connection diagramElement) {
		super(diagramElement);
		
	}
	
	@Override
	public boolean contains(Point p) {
		Point a = getDiagramElement().getFrom().getConnectionPoints().get(0);
		Point b = getDiagramElement().getTo().getConnectionPoints().get(0);
		for(Point p1 : getDiagramElement().getFrom().getConnectionPoints()) {
			for(Point p2 : getDiagramElement().getTo().getConnectionPoints()) {
				if(p1.distance(p2) < a.distance(b)) {
					a = p1;
					b = p2;
				}
			}
		}
		double distance = Line2D.ptSegDistSq(a.x, a.y, b.x, b.y, p.x, p.y);
		return distance < 2;
	}
	@Override
	public Connection getDiagramElement(){
		return (Connection)super.getDiagramElement();
	}
	@Override
	public boolean contains(int x, int y) {
		return contains(new Point(x, y));
	}
	public boolean intersects(Rectangle r) {
		Point a = new Point(getDiagramElement().getFrom().getX(), getDiagramElement().getFrom().getY());
		Point b = new Point(getDiagramElement().getTo().getX(), getDiagramElement().getTo().getY());
		ArrayList<Point> points = new ArrayList<>();
		
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof ConnectionPainter && ((ConnectionPainter) obj).getDiagramElement().getFrom().equals(this.getDiagramElement().getFrom())
			&& ((ConnectionPainter) obj).getDiagramElement().getTo().equals(this.getDiagramElement().getTo());
	}
}
