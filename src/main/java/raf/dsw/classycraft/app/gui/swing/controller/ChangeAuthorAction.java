package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import main.java.raf.dsw.classycraft.app.gui.swing.view.ChangeAuthorView;
import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;


public class ChangeAuthorAction extends AbstractClassyAction{
    private String name;
    ChangeAuthorView changeAuthorView;

    public ChangeAuthorAction(String name, ChangeAuthorView changeAuthorView) {
        this.name = name;
        this.changeAuthorView=changeAuthorView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode().setName(name);
        changeAuthorView.dispose();
    }
}
