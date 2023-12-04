package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;

import java.util.ArrayList;

public class Enum extends InterClass {
	ArrayList<String> contents;
	
	public Enum(ClassyNodeComposite parent, String name, Visibility visibility) {
		super(parent, name, visibility);
		contents = new ArrayList<>();
		((Diagram) getParent()).notifySubscribers("");
	}
	
	public ArrayList<String> getContents() {
		return contents;
	}
	
	public void addEnum(String e) {
		if(e == null)
			return;
		if(!contents.contains(e)) {
			contents.add(e);
		}
		((Diagram) getParent()).notifySubscribers("");
		
	}
	
	public void setContents(ArrayList<String> contents) {
		this.contents = contents;
		((Diagram) getParent()).notifySubscribers("");
		
	}
}
