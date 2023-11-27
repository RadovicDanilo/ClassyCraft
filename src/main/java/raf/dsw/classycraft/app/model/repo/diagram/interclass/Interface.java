package main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass.content.Method;

import java.util.ArrayList;

public class Interface extends InterClass {
    private ArrayList<Method> methods = new ArrayList<>();
    public Interface(ClassyNode parent, String ime, Visibility visibility) {
        super(parent, ime, visibility);
    }
}
