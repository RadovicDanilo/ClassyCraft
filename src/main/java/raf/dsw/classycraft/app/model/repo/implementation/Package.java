package main.java.raf.dsw.classycraft.app.model.repo.implementation;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;

public class Package extends ClassyNodeComposite {
    protected Package(ClassyNode parent, String name) {
        super(parent, name);
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Package))
            return false;
        Package node = (Package) obj;
        return  super.getParent()==node.getParent() && super.getName().equals(node.getName());
    }
}
