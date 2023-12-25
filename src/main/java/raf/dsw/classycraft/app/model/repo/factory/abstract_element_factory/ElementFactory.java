package main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.element_type.ConnectionType;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.element_type.InterClassType;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;
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
    public InterClass createInterClass(InterClassType interClassType, ClassyNodeComposite parent, int x, int y) {
        switch (interClassType) {
            case CLASS:
                c++;
                return new Klasa(parent, "Class" + c, x, y);
            case INTERFACE:
                i++;
                return new Interface(parent, "Interface" + i, x, y);
            case ENUM:
                e++;
                return new Enum(parent, "Enum" + e, x, y);
            default:
                return null;
        }
    }

    @Override
    public Connection createConnection(ConnectionType connectionType, ClassyNodeComposite parent, InterClass from, InterClass to) {
        String x = " from " + from.getName() + " To " + to.getName();
        switch (connectionType) {
            case AGGREGATION:
                a++;
                return new Aggregation(parent, "aggregation " + a + x, from, to);
            case DEPENDENCY:
                d++;
                return new Dependency(parent, "dependency " + d + x, from, to);
            case COMPOSITION:
                co++;
                return new Composition(parent, "composition " + co + x, from, to);
            case GENERALISATION:
                g++;
                return new Generalisation(parent, "generalisation " + g + x, from, to);
            default:
                return null;
        }
    }
}
