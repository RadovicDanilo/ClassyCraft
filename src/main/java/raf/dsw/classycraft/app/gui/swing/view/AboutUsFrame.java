package main.java.raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class AboutUsFrame extends JFrame {
    private static AboutUsFrame instance;

    public void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("About us");

        TextField tfDanilo = new TextField("Danilo Radović 63/22 RN");
        TextField tfOgnjen = new TextField("Ognjen Tasić 139/22 RI");
        //TODO dodati ova tekst polja i slike ako su neophodne.
    }

    public static AboutUsFrame getInstance() {
        if(instance == null)
        {
            instance = new AboutUsFrame();
            instance.initialize();
        }
        return instance;
    }
}
