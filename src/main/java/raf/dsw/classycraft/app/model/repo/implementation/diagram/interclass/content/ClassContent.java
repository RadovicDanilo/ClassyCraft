package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content;

import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;

public abstract class ClassContent {
	private String name;
	private Visibility visibility;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	
	public ClassContent(String name, Visibility visibility) {
		this.name = name;
		this.visibility = visibility;
	}
	
	public String getName() {
		return name;
	}
	
	public Visibility getVisibility() {
		return visibility;
	}
}
