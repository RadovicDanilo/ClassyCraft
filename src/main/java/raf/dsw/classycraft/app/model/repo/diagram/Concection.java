package main.java.raf.dsw.classycraft.app.model.repo.diagram;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;

public abstract class Concection extends DiagramElement{
	
	private InterClass from;
	private InterClass to;
	
	public Concection(ClassyNode parent, String name
		  , InterClass from, InterClass to){
		super(parent, name);
		this.from = from;
		this.to = to;
	}
}
