package main.java.raf.dsw.classycraft.app.model.repo;


import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;


public class ClassyRepositoryImplementation implements ClassyRepository {

    private final ClassyNodeComposite root;

    public ClassyRepositoryImplementation() {
        root = new ProjectExplorer();
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child.getParent() == null) {
            return;
        }
        if (!(child.getParent() instanceof ClassyNodeComposite)) {
            return;
        }
        if (((ClassyNodeComposite) child.getParent()).getChildren().contains(child)) {
            return;
        }
        ((ClassyNodeComposite) child.getParent()).addChild(child);
    }

    @Override
    public void removeChild(ClassyNode child) {
        ((ClassyNodeComposite) child.getParent()).removeChild(child);
    }

    @Override
    public ClassyNode getRoot() {
        return root;
    }

}
