package main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.enumeration.ConnectionType;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.enumeration.InterClassType;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Agregation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Composition;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Dependency;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Generalisation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;

public class ElementFactory extends AbstractElementFactory{
	@Override
	public InterClass createInterClass(InterClassType interClassType, ClassyNodeComposite parent, String name, Visibility visibility) {
		switch(interClassType) {
			case CLASS:
				return new Klasa(parent, name, visibility);
			case INTERFACE:
				return new Interface(parent, name, visibility);
			case ENUM:
				return new Enum(parent, name, visibility);
			default:
				return null;
		}
	}
	
	@Override
	public Connection createConnection(ConnectionType connectionType, ClassyNodeComposite parent, String name, InterClass from, InterClass to) {
		switch(connectionType){
			case AGGREGATION:
				return new Agregation(parent,name,from,to);
			case DEPENDENCY:
				return new Dependency(parent,name,from,to);
			case COMPOSITION:
				return new Composition(parent,name,from,to);
			case GENERALISATION:
				return new Generalisation(parent,name,from,to);
			default:
				return null;
		}
	}
}
