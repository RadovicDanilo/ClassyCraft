package main.java.raf.dsw.classycraft.app.model.repo.factory;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;

public class ProjectFactory {
	private static int counter = 0;
	public ClassyNode classyNode() {
		counter++;
		return new Project("project " + counter);
	}
}
