package main.java.raf.dsw.classycraft.app.model.repo.implementation;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;

public class ClassyNodeFactory {
    public ClassyNodeFactory() {
    }

    public ClassyNode classyNode(NodeType nodeType, String name, ClassyNode parent){
        switch (nodeType){
            case PROJECT_EXPLORER:return new ProjectExplorer(name);
            case PROJECT:return new Project(name);
            case PACKAGE:return new Package(parent,name);
            case DIAGRAM:return new Diagram(parent,name);
        }
        return null;
    }
}
