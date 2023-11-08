package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;

import java.awt.event.ActionEvent;

public class DeleteNodeAction extends AbstractClassyAction{
    public DeleteNodeAction() {
        putValue(SMALL_ICON,  loadIcon("/images/icons/remove_project.png"));//TODO TRASH ICON
        putValue(NAME, "Delete node");
        putValue(SHORT_DESCRIPTION, "Delete node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selectedNode = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selectedNode.getClassyNode() instanceof ProjectExplorer)
            //TODO error nema uklanjanja exlorera
            return;
        ApplicationFramework.getInstance().getClassyRepository().getRoot().removeChild(selectedNode.getClassyNode());
    }
}
