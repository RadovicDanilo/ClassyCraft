package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;

public abstract class Connection extends DiagramElement {
	private final InterClass from;
	private final InterClass to;
	
	public Connection(ClassyNodeComposite parent, String name, InterClass from, InterClass to) {
		super(parent, name);
		this.from = from;
		this.to = to;
	}
	
	public InterClass getFrom() {
		return from;
	}
	
	public InterClass getTo() {
		return to;
	}
}
