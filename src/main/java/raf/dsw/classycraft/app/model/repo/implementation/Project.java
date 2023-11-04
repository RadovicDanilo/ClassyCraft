package main.java.raf.dsw.classycraft.app.model.repo.implementation;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;

import java.io.File;

public class Project extends ClassyNodeComposite {
    private String author;
    private String resourcePath;

    public Project(ClassyNode parent, String ime, String author) {
        super(parent, ime, NodeType.PROJECT);
        this.author = author;
        //TODO ovo cemo uraditi kad budemo radili dodavanje fajlova sto je verovatno ova nedelja
        //this.resourcePath = ApplicationFramework.getInstance().PROJECTS_PATH + this.getName();
        //new File(resourcePath).mkdirs();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
