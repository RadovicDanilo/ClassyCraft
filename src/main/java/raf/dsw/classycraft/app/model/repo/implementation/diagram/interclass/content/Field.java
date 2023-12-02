package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content;

import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;

public class Field extends ClassContent {
	private String type;
	
	public Field(String name, Visibility visibility, String type) {
		super(name, visibility);
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
