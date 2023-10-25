package main.java.raf.dsw.classycraft.app.gui.swing.view;

import main.java.raf.dsw.classycraft.app.gui.swing.controller.ActionManager;
import main.java.raf.dsw.classycraft.app.gui.swing.controller.ExitAction;
import main.java.raf.dsw.classycraft.app.gui.swing.controller.NewProjectAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        editMenu.add(MainFrame.getInstance().getActionManager().getAboutUsAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getChangeThemeAction());

        this.add(fileMenu);
        this.add(editMenu);
    }
}
