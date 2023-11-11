package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import main.java.raf.dsw.classycraft.app.model.message.SystemEvent;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;

import java.awt.event.ActionEvent;

public class DeleteNodeAction extends AbstractClassyAction{
    public DeleteNodeAction() {
        putValue(SMALL_ICON,  loadIcon("/images/icons/remove_project.png"));
        putValue(NAME, "Delete node");
        putValue(SHORT_DESCRIPTION, "Delete node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO OVDE VEROVATNO JTABBEDPANE TREBA DA GLEDA JEL IMA PROMENA

        ClassyTreeItem selectedNode = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selectedNode.getClassyNode() instanceof ProjectExplorer){
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.CANNOT_REMOVE_ROOT);
            return;
        }
        (ApplicationFramework.getInstance().getClassyRepository()).removeChild(selectedNode.getClassyNode());
        ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).removeNode(selectedNode);
    }
}
