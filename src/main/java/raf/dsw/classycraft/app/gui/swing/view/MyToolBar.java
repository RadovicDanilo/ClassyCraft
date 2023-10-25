package main.java.raf.dsw.classycraft.app.gui.swing.view;



import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar(){
        super(HORIZONTAL);
        setFloatable(false);

        add (MainFrame.getInstance().getActionManager().getExitAction());
        add (MainFrame.getInstance().getActionManager().getNewProjectAction());
    }
}
