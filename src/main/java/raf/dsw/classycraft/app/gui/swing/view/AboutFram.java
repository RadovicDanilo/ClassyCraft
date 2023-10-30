package main.java.raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class AboutFram extends JFrame {
    private static AboutFram instance;
    private ImageIcon ognjenSlika;
    private JLabel ognjenSlikaContainer;
    private ImageIcon daniloSlika;
    private JLabel daniloSlikaContainer;
    private JLabel ognjenIme;
    private JLabel daniloIme;
    private AboutFram() throws HeadlessException {
        this.setSize(new Dimension(300,300));
        this.setTitle("Vezba");
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        ognjenSlika = new ImageIcon("src/main/resources/images/ognjentasic.jpg");
        ognjenSlikaContainer = new JLabel(ognjenSlika);
        daniloSlika = new ImageIcon("src/main/resources/images/daniloradovic.jpg");
        daniloSlikaContainer = new JLabel(daniloSlika);
        ognjenIme = new JLabel("Ognjen Tasic 135/23 RN");
        daniloIme = new JLabel("Danilo Radovic 63/22 RN");
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 5;
        c.ipady = 5;
        this.add(ognjenSlikaContainer, c);
        c.gridx = 1;
        this.add(daniloSlikaContainer, c);
        c.gridy = 1;
        this.add(daniloIme, c);
        c.gridx = 0;
        this.add(ognjenIme, c);
    }
    public static AboutFram getInstance(){
        if(instance == null){
            instance = new AboutFram();
        }
        return instance;
    }
}
