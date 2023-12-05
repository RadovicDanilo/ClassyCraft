package main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.enumeration.ConnectionType;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.enumeration.InterClassType;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Aggregation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Composition;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Dependency;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Generalisation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;

public class ElementFactory extends AbstractElementFactory {
	private static int c = 0;
	private static int i = 0;
	private static int e = 0;
	private static int a = 0;
	private static int d = 0;
	private static int co = 0;
	private static int g = 0;
	
	@Override
	public InterClass createInterClass(InterClassType interClassType, ClassyNodeComposite parent, Visibility visibility) {
		switch(interClassType) {
			case CLASS:
				c++;
				return new Klasa(parent, "klasa " + c, visibility);
			case INTERFACE:
				i++;
				return new Interface(parent, "interfejs " + i, visibility);
			case ENUM:
				e++;
				return new Enum(parent, "enum " + e, Visibility.PUBLIC);
			default:
				return null;
		}
	}
	
	@Override
	public Connection createConnection(ConnectionType connectionType, ClassyNodeComposite parent, InterClass from, InterClass to) {
		switch(connectionType) {
			case AGGREGATION:
				a++;
				return new Aggregation(parent, "agr " + a, from, to);
			case DEPENDENCY:
				d++;
				return new Dependency(parent, "dep " + d, from, to);
			case COMPOSITION:
				co++;
				return new Composition(parent, "comp " + co, from, to);
			case GENERALISATION:
				g++;
				return new Generalisation(parent, "gener " + g, from, to);
			default:
				return null;
		}
	}
}
