package main.java.raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class AboutUsFrame extends JFrame {
    //TODO treba poboljsati izgled ovog frame-a. Povecati font i bolji raspored.
    private static AboutUsFrame instance;

    public static AboutUsFrame getInstance() {
        if(instance == null)
        {
            instance = new AboutUsFrame();
            instance.initialize();
        }
        return instance;
    }

    public void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth * 9/10, screenHeight * 9/10);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("About us");
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        ImageIcon ognjenSlika = new ImageIcon("src/main/resources/images/ognjentasic.jpg");  //TODO dodati sliku
        JLabel ognjenSlikaContainer = new JLabel(ognjenSlika);
        ImageIcon daniloSlika = new ImageIcon("src/main/resources/images/daniloradovic.jpg");
        daniloSlika.setDescription("Danilo Radovic 63/22 RN");
        JLabel daniloSlikaContainer = new JLabel(daniloSlika);
        //TODO centrirati tekst.
        JLabel ognjenIme = new JLabel("Ognjen Tasic 135/23 RN");
        JLabel daniloIme = new JLabel("Danilo Radovic 63/22 RN");
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 400;
        c.ipady = 0;
        add(ognjenSlikaContainer, c);
        c.gridx = 1;
        add(daniloSlikaContainer, c);
        c.gridy = 1;
        add(daniloIme, c);
        c.gridx = 0;
        add(ognjenIme, c);

    }

}
