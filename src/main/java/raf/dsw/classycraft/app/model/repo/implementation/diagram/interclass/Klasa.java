package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.ClassContent;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Field;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Method;

import java.util.ArrayList;

public class Klasa extends InterClass {
	private final ArrayList<ClassContent> contents;
	
	public Klasa(ClassyNodeComposite parent, String name, Visibility visibility) {
		super(parent, name, visibility);
		contents = new ArrayList<>();
	}
	
	public ArrayList<ClassContent> getContents() {
		return contents;
	}
	public void addMethod(Method m){
		if(m == null)
			return;
		if(!contents.contains(m)){
			contents.add(m);
		}
	}
	public void addField(Field f){
		if(f == null)
			return;
		if(!contents.contains(f)){
			contents.add(f);
		}
	}
}
