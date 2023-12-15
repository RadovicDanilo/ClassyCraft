package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;

public class Composition extends Connection {
	private Cardinality cardinalityFrom;
	private Cardinality cardinalityTo;
	private String nameFrom;
	private String nameTo;
	private Visibility visibilityFrom;
	private Visibility visibilityTo;
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

	public String getNameFrom() {
		return nameFrom;
	}

	public void setNameFrom(String nameFrom) {
		this.nameFrom = nameFrom;
	}

	public String getNameTo() {
		return nameTo;
	}

	public void setNameTo(String nameTo) {
		this.nameTo = nameTo;
	}

	public Visibility getVisibilityFrom() {
		return visibilityFrom;
	}

	public void setVisibilityFrom(Visibility visibilityFrom) {
		this.visibilityFrom = visibilityFrom;
	}

	public Visibility getVisibilityTo() {
		return visibilityTo;
	}

	public void setVisibilityTo(Visibility visibilityTo) {
		this.visibilityTo = visibilityTo;
	}
}
