package main.java.raf.dsw.classycraft.app.gui.swing.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class AboutUsFrame extends JFrame {
    //TODO ULEPSATI
    private static AboutUsFrame instance;

    public static AboutUsFrame getInstance() {
        if(instance == null)
        {
            instance = new AboutUsFrame();
            instance.initialize();
        }
        return instance;
    }

    public void initialize() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth * 6/10, screenHeight * 6/10);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("About us");
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        BufferedImage daniloSlika = null;
        BufferedImage ognjenSlika = null;
        URL imageURLDanilo = getClass().getResource("/images/about/daniloradovic.png");
        URL imageURLOgnjen = getClass().getResource("/images/about/ognjentasic.jpg");
        try {
            daniloSlika = ImageIO.read(Objects.requireNonNull(imageURLDanilo));
            ognjenSlika = ImageIO.read(Objects.requireNonNull(imageURLOgnjen));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel daniloSlikaContainer = new JLabel(new ImageIcon(daniloSlika));
        JLabel ognjenSlikaContainer = new JLabel(new ImageIcon(ognjenSlika));
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
