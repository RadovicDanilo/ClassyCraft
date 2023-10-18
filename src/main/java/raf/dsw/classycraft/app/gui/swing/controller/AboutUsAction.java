package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutUsAction extends AbstractAction {

    public AboutUsAction() {
        putValue(SMALL_ICON, null); //TODO [LOW] dodaj ikonu za about us
        putValue(NAME, "About us");
        putValue(SHORT_DESCRIPTION, "About us");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO pop up meni sa licnim podacima i slikom.
    }
}
