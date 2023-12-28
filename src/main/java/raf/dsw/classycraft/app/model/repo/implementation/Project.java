package main.java.raf.dsw.classycraft.app.model.repo.implementation;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.observer.notifications.PackageViewEvent;

import java.util.ArrayList;

public class Project extends ClassyNodeComposite {
    private String author;
    private String resourcePath;

    public Project(ClassyNodeComposite parent, String name) {
        super();
    }

    public Project() {

    }

    public Project(String name, ArrayList<ClassyNode> children, String author, String resourcePath) {
        super(null, name);
        this.setChildren(children);
        this.resourcePath = resourcePath;
        this.author = author;


    }

    public Project(String name) {
        super((ClassyNodeComposite) ApplicationFramework.getInstance().getClassyRepository().getRoot(), name);
        this.author = "Nepoznat";
        this.resourcePath = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Project))
            return false;
        ClassyNode node = (ClassyNode) obj;
        return super.getName().equals(node.getName());
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        changeAuthorUpdate((ArrayList<ClassyNode>) this.getChildren());
    }

    public void changeAuthorUpdate(ArrayList<ClassyNode> children) {
        for (ClassyNode classyNode : children) {
            if (classyNode instanceof Package) {
                ((Package) classyNode).notifySubscribers(PackageViewEvent.CHANGE_AUTHOR);
                changeAuthorUpdate((ArrayList<ClassyNode>) ((Package) classyNode).getChildren());
            }
        }
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }


}
