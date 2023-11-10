package main.java.raf.dsw.classycraft.app.gui.swing.view;

import main.java.raf.dsw.classycraft.app.gui.swing.controller.ChangeAuthorAction;

import javax.swing.*;
import java.awt.*;

public class ChangeAuthorView extends JFrame {
    private JTextField tfAuthor;

    public ChangeAuthorView() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth * 2/10, screenHeight * 2/10);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Change author");
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("Ime autora:"),c);
        c.gridy = 1;
        tfAuthor = new JTextField();
        tfAuthor.setPreferredSize(new Dimension(100,30));
        add(tfAuthor,c);
        c.gridy = 2;
        JButton btEnter = new JButton();
        btEnter.setPreferredSize(new Dimension(100,40));
        btEnter.setText("Enter");
        btEnter.setAction(new ChangeAuthorAction(this));
        add(btEnter,c);
    }

    public JTextField getTfAuthor() {
        return tfAuthor;
    }
}
