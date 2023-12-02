package main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass.content;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.InterClass;

public abstract class ClassContent{
	private String name;
	private Visibility visibility;
	
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
