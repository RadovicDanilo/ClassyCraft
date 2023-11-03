package main.java.raf.dsw.classycraft.app.model.repo.abs;

import main.java.raf.dsw.classycraft.app.model.repo.implementation.NodeType;

public abstract class ClassyNode {
    private ClassyNode parent;
    private String ime;
    private NodeType type;

    public ClassyNode(ClassyNode parent, String ime, NodeType type) {
        this.parent = parent;
        this.ime = ime;
        this.type = type;
    }

    public ClassyNode getParent() {return parent;}
    public void setParent(ClassyNode parent) {this.parent = parent;}
    public String getIme() {return ime;}
    public void setIme(String ime) {this.ime = ime;}

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  ClassyNode)) return false;
        ClassyNode node = (ClassyNode) obj;
        return this.ime.equals(node.ime) && this.type.equals(node.type);
    }
}
