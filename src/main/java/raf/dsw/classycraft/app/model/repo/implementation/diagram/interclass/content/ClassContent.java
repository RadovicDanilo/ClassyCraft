package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content;

import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;

public abstract class ClassContent {
    private String name;
    private Visibility visibility;

    public ClassContent(String name, Visibility visibility) {
        this.name = name;
        this.visibility = visibility;
    }

    public ClassContent() {
    }

    public abstract String toCode();
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }
}
