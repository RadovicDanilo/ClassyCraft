package main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass.content.Method;

import java.util.ArrayList;

public class Interface extends InterClass {
	private final ArrayList<Method> methods = new ArrayList<>();
	
	public Interface(ClassyNodeComposite parent, String name, Visibility visibility) {
		super(parent, name, visibility);
	}
}
