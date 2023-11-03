package main.java.raf.dsw.classycraft.app.gui.swing.view;

import main.java.raf.dsw.classycraft.app.gui.swing.controller.ActionManager;
import main.java.raf.dsw.classycraft.app.model.message.Message;
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
        setSize(screenWidth, screenHeight);
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
        //TODO proveriti da li je ispravno, mozda postoji bolji nacin.
        if(!(notification instanceof Message)){
            return;
        }
        JOptionPane messageOptionPane = new JOptionPane();
        messageOptionPane.setMessage(((Message) notification).getText());
        switch (((Message) notification).getMessageType()){
            case INFO: messageOptionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
            break;
            case WARNING: messageOptionPane.setMessageType(JOptionPane.WARNING_MESSAGE);
            break;
            case ERROR: messageOptionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
            break;
        }
        JDialog messageDialog = new JDialog();
        switch (((Message) notification).getSystemEvent()){
            case THEME_CHANGED:messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
                messageDialog = messageOptionPane.createDialog("Promena teme");
                break;
            case NAME_CANNOT_BE_EMPTY:messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
                messageDialog = messageOptionPane.createDialog("Naziv nesme biti prazan");
                break;
            case NODE_CANNOT_BE_DELETED:messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
                messageDialog = messageOptionPane.createDialog("Cvor ne moze biti obrisan");
                break;
        }
        messageDialog.show();
    }
}
