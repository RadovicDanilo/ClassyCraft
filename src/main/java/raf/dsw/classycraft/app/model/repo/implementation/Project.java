package main.java.raf.dsw.classycraft.app.model.repo.implementation;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;

import java.io.File;

public class Project extends ClassyNodeComposite {
    private String author;
    private String resource_path;

    public Project(ClassyNode parent, String ime, NodeType type, String author) {
        super(parent, ime, type);
        this.author = author;
        this.resource_path = "projects/" + this.getIme();
        new File(resource_path).mkdirs();
    }// TODO resiti problem "sta ako directory sa imenom projekta vec postoji"/postarati se da nikad ne dodje do toga
}
