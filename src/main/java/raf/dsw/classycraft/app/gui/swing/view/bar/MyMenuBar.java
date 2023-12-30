package main.java.raf.dsw.classycraft.app.gui.swing.view.bar;


import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        fileMenu.add(MainFrame.getInstance().getActionManager().getOpenAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAsAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewPackageAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewDiagramAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveDiagramAsTemplateAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveDiagramAsScreenshotAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExportProjectAsJavaCodeAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());


        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        editMenu.add(MainFrame.getInstance().getActionManager().getUndoAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getRedoAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getAboutUsAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getChangeAuthorShowViewAction());

        this.add(fileMenu);
        this.add(editMenu);
    }
}
