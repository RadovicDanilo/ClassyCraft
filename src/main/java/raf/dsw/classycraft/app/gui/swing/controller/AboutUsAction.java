package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import main.java.raf.dsw.classycraft.app.gui.swing.view.AboutUsFrame;

import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractClassyAction {

    public AboutUsAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/about.png")); //TODO dodaj ikonu za about us
        putValue(NAME, "About us");
        putValue(SHORT_DESCRIPTION, "About us");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AboutUsFrame.getInstance().setVisible(true);
    }
}
