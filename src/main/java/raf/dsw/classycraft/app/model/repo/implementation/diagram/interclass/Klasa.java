package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.ClassContent;

import java.util.ArrayList;

public class Klasa extends InterClass {
	private final ArrayList<ClassContent> contents = new ArrayList<>();
	
	public Klasa(ClassyNodeComposite parent, String name, Visibility visibility) {
		super(parent, name, visibility);
	}
}
