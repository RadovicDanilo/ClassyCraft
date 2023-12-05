package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;

public abstract class InterClass extends DiagramElement {
	private Visibility visibility;
	
	public InterClass(ClassyNodeComposite parent, String name, Visibility visibility) {
		super(parent, name);
		this.visibility = visibility;
	}

	public Visibility getVisibility() {
		return visibility;
	}
	
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
}
