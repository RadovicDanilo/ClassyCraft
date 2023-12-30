package main.java.raf.dsw.classycraft.app.model.repo.abs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Aggregation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Composition;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Dependency;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Generalisation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.observer.notifications.PackageViewEvent;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

import java.util.ArrayList;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "node_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Project.class, name = "project"),
        @JsonSubTypes.Type(value = Package.class, name = "package"),
        @JsonSubTypes.Type(value = Diagram.class, name = "diagram"),

        @JsonSubTypes.Type(value = Klasa.class, name = "klasa"),
        @JsonSubTypes.Type(value = Interface.class, name = "interfejs"),
        @JsonSubTypes.Type(value = Enum.class, name = "enumeracija"),

        @JsonSubTypes.Type(value = Aggregation.class, name = "aggregation"),
        @JsonSubTypes.Type(value = Composition.class, name = "composition"),
        @JsonSubTypes.Type(value = Generalisation.class, name = "generalisation"),
        @JsonSubTypes.Type(value = Dependency.class, name = "dependency"),
})
public abstract class ClassyNode {
    @JsonIgnore
    private ClassyNodeComposite parent;
    private String name;

    public ClassyNode(ClassyNodeComposite parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public ClassyNode() {

    }

    @Override
    public String toString() {
        return name;
    }

    public ClassyNode getParent() {
        return parent;
    }

    public void setParent(ClassyNodeComposite parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNodeName(String name) {
        if (this instanceof InterClass && (name == null || name.length() == 0 || !name.substring(0, 1).matches("[a-zA-Z]+") || !name.matches("^([\\w+\\-/])+$"))) {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.INTERCLASS_NAME_NOT_VALID);
            return;
        }
        for (ClassyNode c : this.parent.getChildren()) {
            if (this.name.equals(name)) {
                break;
            }
            if (c.getName().equals(name)) {
                ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.NODE_CANNOT_BE_DUPLICATE);
                return;
            }
        }
        this.name = name;
        getParent().changed();
        if (this instanceof DiagramElement) {
            ((Diagram) (this.getParent())).notifySubscribers("");
        } else if (this instanceof Diagram) {
            ((Package) this.getParent()).notifySubscribers(PackageViewEvent.RENAME_DIAGRAM);
        } else if (this instanceof Project) {
            changeProjectNameUpdate((ArrayList<ClassyNode>) ((Project) this).getChildren());
        }
    }

    public void changeProjectNameUpdate(ArrayList<ClassyNode> children) {
        for (ClassyNode classyNode : children) {
            if (classyNode instanceof Package) {
                ((Package) classyNode).notifySubscribers(PackageViewEvent.RENAME_PROJECT);
                changeProjectNameUpdate((ArrayList<ClassyNode>) ((Package) classyNode).getChildren());
            }
        }
    }
    public void changed() {
        if(this instanceof ProjectExplorer){
            return;
        }
        ClassyNode project = this;
        while (!(project instanceof Project)) {
            project = project.getParent();
        }
        ((Project) project).setChanged(true);
    }
}
