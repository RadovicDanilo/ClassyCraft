package main.java.raf.dsw.classycraft.app.model.repo.factory;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;

public class PackageFactory{
	public ClassyNode classyNode(String name, ClassyNode parent){
		return new Package(parent, name);
	}
}
