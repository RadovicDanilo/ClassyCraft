package main.java.raf.dsw.classycraft.app.model.repo.factory;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;

public class PackageFactory {
	private static int counter = 0;
	public ClassyNode classyNode(ClassyNodeComposite parent) {
		counter++;
		return new Package(parent, "Package " + counter);
	}
}
