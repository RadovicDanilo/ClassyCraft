package main.java.raf.dsw.classycraft.app.gui.swing.view;

import main.java.raf.dsw.classycraft.app.gui.swing.controller.ChangeAuthorAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChangeAuthorView extends JFrame {
    private static ChangeAuthorView instance;

    public static ChangeAuthorView getInstance() {
        if(instance == null)
        {
            instance = new ChangeAuthorView();
            instance.initialize();
        }
        return instance;
    }

    private void initialize() {
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
        JTextField tfAuthor = new JTextField();
        tfAuthor.setSize(200,100);
        tfAuthor.setMinimumSize(new Dimension(200,100));
        add(tfAuthor,c);
        c.gridy = 2;
        JButton btEnter = new JButton();
        btEnter.setSize(200,100);
        btEnter.setMinimumSize(new Dimension(200,100));
        btEnter.setText("Promeni ime autora");
        btEnter.setAction(new ChangeAuthorAction(tfAuthor.getText(),this));
        add(btEnter,c);
        instance.setMinimumSize(instance.getMinimumSize());
    }
}
