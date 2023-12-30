package main.java.raf.dsw.classycraft.app.gui.swing.tree.controller;


import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;


public class ClassyTreeSelectionListener implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        ClassyTreeItem treeItemSelected = (ClassyTreeItem) path.getLastPathComponent();
        System.out.println("Selektovan cvor:" + treeItemSelected.getClassyNode().getName());
        System.out.println("getPath: " + e.getPath());
        if(treeItemSelected.getClassyNode() instanceof ProjectExplorer){
            MainFrame.getInstance().getActionManager().getSaveAction().disable();
            MainFrame.getInstance().getActionManager().getSaveAsAction().disable();
            return;
        }
        MainFrame.getInstance().getActionManager().getSaveAsAction().enable();
        ClassyNode project = treeItemSelected.getClassyNode();
        while(!(project instanceof Project)){
            project = project.getParent();
        }
        if(((Project) project).isChanged()){
            MainFrame.getInstance().getActionManager().getSaveAction().enable();
        }else{
            MainFrame.getInstance().getActionManager().getSaveAction().disable();
        }
        if(((Project) project).getResourcePath() == null){
            MainFrame.getInstance().getActionManager().getSaveAction().enable();
        }

    }
}


