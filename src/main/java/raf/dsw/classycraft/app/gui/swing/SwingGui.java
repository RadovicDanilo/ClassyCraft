package main.java.raf.dsw.classycraft.app.gui.swing;

import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;

public class SwingGui{
    //TODO da li je ovo nepohodno? Mozda moze da se ukloni?
    public void start() {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
    }

}
