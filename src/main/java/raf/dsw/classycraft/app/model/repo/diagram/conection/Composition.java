package main.java.raf.dsw.classycraft.app.model.repo.diagram.conection;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.Concection;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.InterClass;

public class Composition extends Concection {
	public Composition(ClassyNodeComposite parent, String name, InterClass from, InterClass to) {
		super(parent, name, from, to);
	}
}
