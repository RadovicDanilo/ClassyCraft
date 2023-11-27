package main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.Visibility;

import java.util.ArrayList;

public class Class extends InterClass {
    private ArrayList<ClassContent> contents = new ArrayList<>();
    public Class(ClassyNode parent, String ime, Visibility visibility) {
        super(parent, ime, visibility);
    }
}
