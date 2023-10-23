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

        //TODO dodati slike, napraviti bolji layout, povecati font na tf-ovim i ulepsati.
        
        JTextField tfDanilo = new JTextField("Danilo Radović 63/22 RN");
        JTextField tfOgnjen = new JTextField("Ognjen Tasić 139/22 RI");
        instance.add(tfDanilo);
        instance.add(tfOgnjen);


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
