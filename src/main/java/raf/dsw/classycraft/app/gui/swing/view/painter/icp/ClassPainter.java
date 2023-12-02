package main.java.raf.dsw.classycraft.app.gui.swing.view.painter.icp;

import main.java.raf.dsw.classycraft.app.gui.swing.view.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass.content.ClassContent;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass.content.Field;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass.content.Method;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class ClassPainter extends ElementPainter {
	// - private
	// + public
	// # protected
	// ~ default
	private Klasa klasa;
	
	public ClassPainter(Point starPoint, Klasa klasa) {
		super(starPoint);
		this.klasa = klasa;
	}
	
	@Override
	public void draw(Graphics2D g) {
		ArrayList<Method> methods = new ArrayList<>();
		ArrayList<Field> fields = new ArrayList<>();
		for(ClassContent c : klasa.getContents()) {
			if(c instanceof Method) {
				methods.add((Method) c);
			}else {
				fields.add((Field) c);
			}
		}
		String s = "<html>" + klasa.getName();
		for(Field f : fields) {
			String visibility = "+";
			switch(f.getVisibility()) {
				case PUBLIC:
					visibility = "+";
					break;
				case PRIVATE:
					visibility = "-";
					break;
				case PROTECTED:
					visibility = "#";
					break;
				case DEFAULT:
					visibility = "~";
					break;
			}
			
			s += visibility + " " + f.getName() + ": " + f.getType() + "<br>";
		}
		s += "-------------<br>";
		for(Method m : methods) {
			String visibility = "+";
			switch(m.getVisibility()) {
				case PUBLIC:
					visibility = "+";
					break;
				case PRIVATE:
					visibility = "-";
					break;
				case PROTECTED:
					visibility = "#";
					break;
				case DEFAULT:
					visibility = "~";
					break;
			}
			String args = "";
			for(String arg : m.getArgs()) {
				args += arg + ", ";
			}
			s += visibility + " " + m.getName() + "(" + args + "): " + m.getReturnValue() + "<br>";
		}
		g.setBackground(Color.BLACK);
		g.drawString(s, getStarPoint().x, getStarPoint().y);
		g.drawRect(super.getStarPoint().x, super.getStarPoint().y, 150, 200);
	}
	
}
