package main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.InterClass;

public abstract class ClassContent extends InterClass{
	public ClassContent(ClassyNode parent, String name, Visibility visibility){
		super(parent, name, visibility);
	}
}
