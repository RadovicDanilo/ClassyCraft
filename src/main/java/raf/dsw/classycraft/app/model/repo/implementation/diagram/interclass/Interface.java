package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Method;

import java.util.ArrayList;

public class Interface extends InterClass {
	private final ArrayList<Method> methods;
	
	public Interface(ClassyNodeComposite parent, String name, Visibility visibility) {
		super(parent, name, visibility);
		methods = new ArrayList<>();
	}
	
	public ArrayList<Method> getMethods() {
		return methods;
	}
	public void addMethod(Method m){
		if(m == null)
			return;
		if(!methods.contains(m)){
			methods.add(m);
		}
	}
}
