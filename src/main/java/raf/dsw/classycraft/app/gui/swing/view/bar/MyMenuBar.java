package main.java.raf.dsw.classycraft.app.gui.swing.view.bar;



import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewPackageAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewDiagramAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getChangeAuthorShowViewAction());

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        editMenu.add(MainFrame.getInstance().getActionManager().getAboutUsAction());

        this.add(fileMenu);
        this.add(editMenu);
    }
}
