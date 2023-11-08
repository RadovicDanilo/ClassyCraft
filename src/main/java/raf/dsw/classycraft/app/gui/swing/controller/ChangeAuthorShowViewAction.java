package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import main.java.raf.dsw.classycraft.app.gui.swing.view.ChangeAuthorView;
import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;

import java.awt.event.ActionEvent;

public class ChangeAuthorShowViewAction extends AbstractClassyAction{
    public ChangeAuthorShowViewAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/user_plus.png"));
        putValue(NAME, "Change author");
        putValue(SHORT_DESCRIPTION, "Change author");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getClassyTree().getSelectedNode()==null){
            return;
        }
        ClassyNode classyNode = MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
        if(classyNode instanceof Project){
            ChangeAuthorView.getInstance().setVisible(true);
        }
    }
}
