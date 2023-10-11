package main.java.raf.dsw.classycraft.app.core;

import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;

public class ApplicationFramework {

    private static ApplicationFramework instance;

    //buduca polja za model celog projekta

    private ApplicationFramework(){

    }

    public void initialize(){
        MainFrame.getInstance().setVisible(true);
    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
