package main.java.raf.dsw.classycraft.app.model.repo;


import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ClassyNodeFactory;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.NodeType;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;


public class ClassyRepositoryImplementation implements ClassyRepository {

    private final ClassyNode root;
    private final ClassyNodeFactory classyNodeFactory = new ClassyNodeFactory();

    public ClassyRepositoryImplementation() {
        root = classyNodeFactory.classyNode(NodeType.PROJECT_EXPLORER,"Project explorer",null);
    }

    @Override
    public ClassyNode getRoot() {
        return root;
    }
    //TODO OVE METODE MORAJU DA SE KORISTE A DA ONE POZIVAJU ONE IZ COMPOSITE
    @Override
    public void addChild(ClassyNode child) {
        if(!(child.getParent() instanceof ClassyNodeComposite))
            return;
        ((ClassyNodeComposite)child.getParent()).addChild(child);
    }
    @Override
    public void removeChild(ClassyNode child) {
        if(!(child.getParent() instanceof ClassyNodeComposite))
            return;
        ((ClassyNodeComposite)child.getParent()).removeChild(child);
    }

    public ClassyNodeFactory getClassyNodeFactory() {
        return classyNodeFactory;
    }
}
