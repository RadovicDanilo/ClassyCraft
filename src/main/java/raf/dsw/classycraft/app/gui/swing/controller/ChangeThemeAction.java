package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import main.java.raf.dsw.classycraft.app.model.message.Message;
import main.java.raf.dsw.classycraft.app.model.message.MessageType;
import main.java.raf.dsw.classycraft.app.model.message.SystemEvent;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ChangeThemeAction extends AbstractClassyAction{
    public ChangeThemeAction() {
        putValue(SMALL_ICON, null); //TODO dodaj ikonu za theme
        putValue(NAME, "Switch theme");
        putValue(SHORT_DESCRIPTION, "Switch theme");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        BufferedWriter bw;
        try (PrintWriter pw = new PrintWriter(ApplicationFramework.getInstance().settingsPath)) {
            bw = new BufferedWriter(pw);
            bw.write("isDarktheme=" + !ApplicationFramework.getInstance().isDarkTheme());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(MainFrame.getInstance());
        ApplicationFramework.getInstance().getMessageGenerator().notifySubscribers(new Message(MessageType.INFO,"Promene ce se primenit pri ponovno pokretanju aplikacije",SystemEvent.THEME_CHANGED));
    }
}
