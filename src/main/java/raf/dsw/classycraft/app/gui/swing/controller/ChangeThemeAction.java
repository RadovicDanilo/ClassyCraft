package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.model.message.Message;
import main.java.raf.dsw.classycraft.app.model.message.MessageType;
import main.java.raf.dsw.classycraft.app.model.message.SystemEvent;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ChangeThemeAction extends AbstractClassyAction{
    public ChangeThemeAction() {
        putValue(SMALL_ICON, null); //TODO dodaj ikonu za theme
        putValue(NAME, "Switch theme");
        putValue(SHORT_DESCRIPTION, "Switch theme");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ApplicationFramework.getInstance().SETTINGS_PATH));
            writer.write("isDarkTheme="+!ApplicationFramework.getInstance().isDarkTheme());
            writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        ApplicationFramework.getInstance().getMessageGenerator().notifySubscribers(new Message(SystemEvent.THEME_CHANGED, MessageType.INFO,"Promene ce se primenit pri ponovno pokretanju aplikacije"));
    }
}
