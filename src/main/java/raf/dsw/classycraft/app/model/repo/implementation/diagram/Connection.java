package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Aggregation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Composition;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Dependency;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Generalisation;

public abstract class Connection extends DiagramElement {
	private final InterClass from;
	private final InterClass to;
	
	public Connection(ClassyNodeComposite parent, String name, InterClass from, InterClass to) {
		super(parent, name);
		this.from = from;
		this.to = to;
	}
	
	@Override
	public String getName() {
		String a = "";
		String b = " from " + from.getName() + " To " + to.getName();
		if(this instanceof Aggregation) {
			a = "Aggregation";
		}
		if(this instanceof Composition) {
			a = "Composition";
		}
		if(this instanceof Dependency) {
			a = "Dependency";
		}
		if(this instanceof Generalisation) {
			a = "Generalisation";
		}
		return a + b;
	}
	
	public InterClass getFrom() {
		return from;
	}
	
	public InterClass getTo() {
		return to;
	}
}