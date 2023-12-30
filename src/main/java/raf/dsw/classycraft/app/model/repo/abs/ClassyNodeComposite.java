package main.java.raf.dsw.classycraft.app.model.repo.abs;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.observer.notifications.PackageViewEvent;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "node_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Project.class, name = "project"),
        @JsonSubTypes.Type(value = Package.class, name = "package"),
        @JsonSubTypes.Type(value = Diagram.class, name = "diagram"),
})
public abstract class ClassyNodeComposite extends ClassyNode {
    private List<ClassyNode> children = new ArrayList<>();

    public ClassyNodeComposite(ClassyNodeComposite parent, String name) {
        super(parent, name);
    }

    public ClassyNodeComposite() {
        super();
    }

    public void addChild(ClassyNode c) {
        getParent().changed();
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        if (this.children.contains(c)) {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.NODE_CANNOT_BE_DUPLICATE);
            return;
        }

        this.children.add(c);

        if (c instanceof Diagram) {
            ((Package) this).notifySubscribers(PackageViewEvent.ADD_DIAGRAM);
        }
        if (c instanceof DiagramElement) {
            ((Diagram) c.getParent()).notifySubscribers("");
        }
    }


    public void removeChild(ClassyNode c) {
        getParent().changed();
        this.children.remove(c);
        if (c instanceof DiagramElement) {
            ((Diagram) this).notifySubscribers(c);
        }
        if (c instanceof Diagram) {
            ((Package) this).notifySubscribers(PackageViewEvent.REMOVE_DIAGRAM);
        }
        if (c instanceof Package) {
            ((Package) c).notifySubscribers(PackageViewEvent.REMOVE_ALL);
            deleteNodeUpdate((ArrayList<ClassyNode>) ((Package) c).getChildren());
        }
        if (c instanceof Project) {
            deleteNodeUpdate((ArrayList<ClassyNode>) ((Project) c).getChildren());
        }
    }

    public void deleteNodeUpdate(ArrayList<ClassyNode> children) {
        for (ClassyNode classyNode : children) {
            if (classyNode instanceof Package) {
                ((Package) classyNode).notifySubscribers(PackageViewEvent.REMOVE_ALL);
                deleteNodeUpdate((ArrayList<ClassyNode>) ((Package) classyNode).getChildren());
            }
        }
    }

    public List<ClassyNode> getChildren() {
        return children;
    }

    public void setChildren(List<ClassyNode> children) {
        this.children = children;
    }
}
