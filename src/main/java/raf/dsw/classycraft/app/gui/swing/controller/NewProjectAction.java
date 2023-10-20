package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractClassyAction{
    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,null);//TODO dodati ikonu za new project
        putValue(NAME, "New project");
        putValue(SHORT_DESCRIPTION, "New project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
