package main.java.raf.dsw.classycraft.app.gui.swing.view;

import main.java.raf.dsw.classycraft.app.gui.swing.controller.ActionManager;
import main.java.raf.dsw.classycraft.app.model.message.Message;
import main.java.raf.dsw.classycraft.app.model.message.MessageGenerator;
import main.java.raf.dsw.classycraft.app.model.message.SystemEvent;
import main.java.raf.dsw.classycraft.app.model.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("ALL")
public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar toolBar;
    private MainFrame(){

    }

    private void initialize(){
        actionManager = new ActionManager();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        MyMenuBar menu = new MyMenuBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);
    }

    public static MainFrame getInstance()
    {
        if(instance == null)
        {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }
    public ActionManager getActionManager() {
        return actionManager;
    }

    @Override
    public void update(Object notification) {
        if(!(notification instanceof Message)){
            return;
        }
        if (((Message) notification).getSystemEvent() == SystemEvent.THEME_CHANGED) {
            JOptionPane optionPane = new JOptionPane(((Message) notification).getText(),JOptionPane.INFORMATION_MESSAGE,JOptionPane.DEFAULT_OPTION);
            JDialog dialog = optionPane.createDialog("change theme");
            dialog.show();
        }
    }
}
