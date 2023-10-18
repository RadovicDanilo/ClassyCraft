package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public abstract class AbstractClassyAction extends AbstractAction{

    public Icon loadIcon(String fileName){
        URL imageURL = getClass().getResource(fileName);
        Icon icon = null;
        if(imageURL!=null)
            icon = new ImageIcon(imageURL);
        else
            System.out.println("Resource not found " + imageURL);
        return icon;
    }

}
