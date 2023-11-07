package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;

import java.awt.event.ActionEvent;

public class NewPackageAction extends AbstractClassyAction{
    public NewPackageAction() {
        putValue(SMALL_ICON,  loadIcon("/images/icons/add_package.png"));
        putValue(NAME, "New package");
        putValue(SHORT_DESCRIPTION, "New package");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selectedNode = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(!(selectedNode.getClassyNode() instanceof Project || selectedNode.getClassyNode() instanceof Package)){
            return;
            //TODO ERROR
        }
        ClassyNode classyNode = null;
        int i = 0;
        while(true){
            classyNode = new Package(ApplicationFramework.getInstance().getClassyRepository().getProjectExplorer(), "package " + i);
            if(!((ClassyNodeComposite) selectedNode.getClassyNode()).getChildren().contains(classyNode)){
                ((ClassyNodeComposite) selectedNode.getClassyNode()).getChildren().add(classyNode);
                break;
            }
            i++;
        }
        MainFrame.getInstance().getClassyTree().addChild(selectedNode,new ClassyTreeItem(classyNode));
    }
}
