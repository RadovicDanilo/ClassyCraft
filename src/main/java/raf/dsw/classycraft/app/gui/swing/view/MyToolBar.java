package main.java.raf.dsw.classycraft.app.gui.swing.view;



import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar(){
        super(HORIZONTAL);
        setFloatable(false);

        add (MainFrame.getInstance().getActionManager().getExitAction());
        add (MainFrame.getInstance().getActionManager().getNewProjectAction());
        add (MainFrame.getInstance().getActionManager().getNewPackageAction());
        add (MainFrame.getInstance().getActionManager().getNewDiagramAction());
        add (MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        add (MainFrame.getInstance().getActionManager().getChangeAuthorShowViewAction());
        add (MainFrame.getInstance().getActionManager().getAboutUsAction());
        add (MainFrame.getInstance().getActionManager().getChangeThemeAction());
    }
}
