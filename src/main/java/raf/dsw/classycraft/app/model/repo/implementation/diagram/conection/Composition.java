package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;

public class Composition extends Connection {
	private Cardinality cardinalityFrom;
	private Cardinality cardinalityTo;
	public Composition(ClassyNodeComposite parent, String name, InterClass from, InterClass to) {
		super(parent, name, from, to);
	}

	public Cardinality getCardinalityFrom() {
		return cardinalityFrom;
	}

	public void setCardinalityFrom(Cardinality cardinalityFrom) {
		this.cardinalityFrom = cardinalityFrom;
	}

	public Cardinality getCardinalityTo() {
		return cardinalityTo;
	}

	public void setCardinalityTo(Cardinality cardinalityTo) {
		this.cardinalityTo = cardinalityTo;
	}
}
