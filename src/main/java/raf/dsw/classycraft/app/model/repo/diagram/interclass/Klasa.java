package main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass.content.ClassContent;

import java.util.ArrayList;

public class Klasa extends InterClass {
	private final ArrayList<ClassContent> contents = new ArrayList<>();
	
	public Klasa(ClassyNodeComposite parent, String name, Visibility visibility) {
		super(parent, name, visibility);
	}
}
