package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.ClassyRepositoryImplementation;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.NodeType;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractClassyAction{
    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,  loadIcon("/images/icons/add_project.png"));
        putValue(NAME, "New project");
        putValue(SHORT_DESCRIPTION, "New project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyNode classyNode;
        int i = 0;
        while(true){
            classyNode = ((ClassyRepositoryImplementation)ApplicationFramework.getInstance().getClassyRepository()).getClassyNodeFactory().classyNode(NodeType.PROJECT, "project " + i, null);
            if(!((ClassyNodeComposite)ApplicationFramework.getInstance().getClassyRepository().getRoot()).getChildren().contains(classyNode)){
                ApplicationFramework.getInstance().getClassyRepository().addChild(classyNode);
                break;
            }
            i++;
        }
        MainFrame.getInstance().getClassyTree().addChild(((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).getRoot(),new ClassyTreeItem(classyNode));
        //TODO SAMO ROOT OVDE

    }
}
