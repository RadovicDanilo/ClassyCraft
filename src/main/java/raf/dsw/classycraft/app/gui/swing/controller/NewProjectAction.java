package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.factory.ProjectFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractClassyAction{
    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        putValue(SMALL_ICON,  loadIcon("/images/icons/add_project.png"));
        putValue(NAME, "New project");
        putValue(SHORT_DESCRIPTION, "New project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO OVDE VEROVATNO JTABBEDPANE TREBA DA GLEDA JEL IMA PROMENA

        ClassyNode classyNode;
        int i = 0;
        while(true){
            ProjectFactory projectFactory = new ProjectFactory();
            classyNode = projectFactory.classyNode("project "+i);
            if(!((ClassyNodeComposite)ApplicationFramework.getInstance().getClassyRepository().getRoot()).getChildren().contains(classyNode)){
                ApplicationFramework.getInstance().getClassyRepository().addChild(classyNode);
                break;
            }
            i++;
        }
        MainFrame.getInstance().getClassyTree().addChild(((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).getRoot(), classyNode);
    }
}
