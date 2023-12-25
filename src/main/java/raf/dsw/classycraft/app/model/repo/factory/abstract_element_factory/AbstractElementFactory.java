package main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.element_type.ConnectionType;
import main.java.raf.dsw.classycraft.app.model.repo.factory.abstract_element_factory.element_type.InterClassType;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;

public abstract class AbstractElementFactory {
    public abstract InterClass createInterClass(InterClassType interClassType, ClassyNodeComposite parent, int x, int y);

    public abstract Connection createConnection(ConnectionType connectionType, ClassyNodeComposite parent, InterClass from, InterClass to);
}
