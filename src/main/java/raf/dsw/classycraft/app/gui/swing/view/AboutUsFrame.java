package main.java.raf.dsw.classycraft.app.gui.swing.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class AboutUsFrame extends JFrame {
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
        setSize(screenWidth * 4/10, screenHeight * 6/10);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("About us");

        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.ipadx = 64;
        BufferedImage daniloSlika;
        BufferedImage ognjenSlika;
        URL imageURLDanilo = getClass().getResource("/images/about/daniloradovic.png");
        URL imageURLOgnjen = getClass().getResource("/images/about/ognjentasic.jpg");
        try {
            daniloSlika = ImageIO.read(Objects.requireNonNull(imageURLDanilo));
            ognjenSlika = ImageIO.read(Objects.requireNonNull(imageURLOgnjen));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JLabel lbSlikaOgnjen =new JLabel(new ImageIcon(ognjenSlika));
        lbSlikaOgnjen.setVerticalAlignment(SwingConstants.CENTER);
        lbSlikaOgnjen.setHorizontalAlignment(SwingConstants.CENTER);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(lbSlikaOgnjen,gridBagConstraints);

        JLabel lbSlikaDanilo =new JLabel(new ImageIcon(daniloSlika));
        lbSlikaDanilo.setVerticalAlignment(SwingConstants.CENTER);
        lbSlikaDanilo.setHorizontalAlignment(SwingConstants.CENTER);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        add(lbSlikaDanilo,gridBagConstraints);

        JLabel lbOgnjen = new JLabel("Ognjen Tasić 135/23 RN");
        lbOgnjen.setFont(new Font("Calibri", Font.BOLD, 20));
        lbOgnjen.setVerticalAlignment(SwingConstants.CENTER);
        lbOgnjen.setHorizontalAlignment(SwingConstants.CENTER);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(lbOgnjen,gridBagConstraints);

        JLabel lbDanilo = new JLabel("Danilo Radović 63/22 RN");
        lbDanilo.setFont(new Font("Calibri", Font.BOLD, 20));
        lbDanilo.setVerticalAlignment(SwingConstants.CENTER);
        lbDanilo.setHorizontalAlignment(SwingConstants.CENTER);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        add(lbDanilo,gridBagConstraints);
    }
}
