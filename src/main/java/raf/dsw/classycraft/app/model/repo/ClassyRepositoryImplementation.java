package main.java.raf.dsw.classycraft.app.model.repo;


import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;


public class ClassyRepositoryImplementation implements ClassyRepository {

    private ProjectExplorer projectExplorer;

    public ClassyRepositoryImplementation() {
        projectExplorer = new ProjectExplorer("My Project Explorer");
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

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
}
