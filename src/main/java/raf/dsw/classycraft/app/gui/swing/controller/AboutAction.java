package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import main.java.raf.dsw.classycraft.app.gui.swing.view.AboutFram;
import main.java.raf.dsw.classycraft.app.gui.swing.view.AboutUsFrame;

import java.awt.event.ActionEvent;

public class AboutAction extends AbstractClassyAction{
    public AboutAction() {
        putValue(NAME, "About Us 2");
    }

    @Override
    public void actionPerformed(ActionEvent e){
        AboutFram.getInstance().setVisible(true);
    }
}
