package main.java.raf.dsw.classycraft.app.model.repo.abs;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Aggregation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Composition;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Dependency;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Generalisation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "node_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Klasa.class, name = "klasa"),
        @JsonSubTypes.Type(value = Interface.class, name = "interfejs"),
        @JsonSubTypes.Type(value = Enum.class, name = "enumeracija"),

        @JsonSubTypes.Type(value = Aggregation.class, name = "aggregation"),
        @JsonSubTypes.Type(value = Composition.class, name = "composition"),
        @JsonSubTypes.Type(value = Generalisation.class, name = "generalisation"),
        @JsonSubTypes.Type(value = Dependency.class, name = "dependency"),
})
public abstract class ClassyNodeLeaf extends ClassyNode {
    public ClassyNodeLeaf(ClassyNodeComposite parent, String name) {
        super(parent, name);
    }
    public ClassyNodeLeaf() {

    }

}
