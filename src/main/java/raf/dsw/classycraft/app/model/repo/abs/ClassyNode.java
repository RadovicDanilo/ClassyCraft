package main.java.raf.dsw.classycraft.app.model.repo.abs;

public abstract class ClassyNode {
    private ClassyNode parent;
    private String name;
    public ClassyNode(ClassyNode parent, String name) {
        this.parent = parent;
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }

    public ClassyNode getParent() {
        return parent;
    }

    public void setParent(ClassyNode parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
