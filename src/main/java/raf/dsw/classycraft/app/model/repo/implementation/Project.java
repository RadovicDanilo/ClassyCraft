package main.java.raf.dsw.classycraft.app.model.repo.implementation;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.model.observer.IPublisher;
import main.java.raf.dsw.classycraft.app.model.observer.ISubscriber;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;

public class Project extends ClassyNodeComposite {
    private String author;
    private String resourcePath;

    public Project(String name) {
        super((ClassyNodeComposite) ApplicationFramework.getInstance().getClassyRepository().getRoot(), name);
        this.author = "Autor";
        this.resourcePath = ApplicationFramework.getInstance().PROJECTS_PATH + name;
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
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

}
