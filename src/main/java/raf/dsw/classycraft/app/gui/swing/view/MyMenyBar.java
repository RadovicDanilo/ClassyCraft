package main.java.raf.dsw.classycraft.app.gui.swing.view;

import main.java.raf.dsw.classycraft.app.controller.ExitAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MyMenyBar extends JMenuBar {

    public MyMenyBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        ExitAction ea = new ExitAction();
        fileMenu.add(ea);
        add(fileMenu);
    }

}
