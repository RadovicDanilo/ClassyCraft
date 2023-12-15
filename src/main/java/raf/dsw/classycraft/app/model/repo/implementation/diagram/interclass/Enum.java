package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;

import java.util.ArrayList;

public class Enum extends InterClass {
	ArrayList<String> contents;
	
	public Enum(ClassyNodeComposite parent, String name, int x, int y) {
		super(parent, name, x, y);
		contents = new ArrayList<>();
		((Diagram) getParent()).notifySubscribers("");
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Enum) {
			return ((Enum) obj).getName().equals(this.getName());
		}
		return false;
	}
	
	public ArrayList<String> getContents() {
		return contents;
	}
	
	public void setContents(ArrayList<String> contents) {
		this.contents = contents;
		((Diagram) getParent()).notifySubscribers("");
		
	}
	
	public void addEnum(String e) {
		if(e == null)
			return;
		if(!contents.contains(e)) {
			contents.add(e);
			((Diagram) getParent()).notifySubscribers("");
		}
		
	}
}
