package main.java.raf.dsw.classycraft.app.gui.swing;

import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;

public class SwingGui{

    private MainFrame mainFrame;


    public void start() {
        mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
    }

}
