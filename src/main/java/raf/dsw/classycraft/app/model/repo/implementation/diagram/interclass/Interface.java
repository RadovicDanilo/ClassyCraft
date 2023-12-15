package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Method;

import java.util.ArrayList;

public class Interface extends InterClass {
	private ArrayList<Method> methods;
	
	public Interface(ClassyNodeComposite parent, String name, int x, int y) {
		super(parent, name, x, y);
		this.methods = new ArrayList<>();
	}
	
	public ArrayList<Method> getMethods() {
		return methods;
	}
	
	public void setMethods(ArrayList<Method> methods) {
		this.methods = methods;
		((Diagram) getParent()).notifySubscribers("");
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Interface) {
			return ((Interface) obj).getName().equals(this.getName());
		}
		return false;
	}
	
	public void addMethod(Method m) {
		if(m == null)
			return;
		if(!methods.contains(m)) {
			methods.add(m);
		}
		((Diagram) getParent()).notifySubscribers("");
	}
}
