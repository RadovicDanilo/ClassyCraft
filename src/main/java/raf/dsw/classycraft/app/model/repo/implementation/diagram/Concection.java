package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;

public abstract class Concection extends DiagramElement {
	
	private final InterClass from;
	private final InterClass to;
	
	public Concection(ClassyNodeComposite parent, String name
		  , InterClass from, InterClass to) {
		super(parent, name);
		this.from = from;
		this.to = to;
	}
}
