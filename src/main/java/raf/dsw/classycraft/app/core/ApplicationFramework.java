package main.java.raf.dsw.classycraft.app.core;

import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import main.java.raf.dsw.classycraft.app.model.message.MessageGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class ApplicationFramework {
    public final String settingsPath = "src/main/resources/settings/settings.txt";
    private static ApplicationFramework instance;

    private boolean isDarkTheme;
    private MessageGenerator messageGenerator = new MessageGenerator();

    private ApplicationFramework(){

    }

    public void initialize(){
        MainFrame.getInstance().setVisible(true);
    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }

    public void loadThemeSettings() {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(settingsPath);
            br = new BufferedReader(fr);
            String[] darkThemeSetting = br.readLine().split("=");
            this.isDarkTheme= Objects.equals(darkThemeSetting[1], "true");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                assert br != null;
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                fr.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isDarkTheme() {
        return isDarkTheme;
    }
    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }

}
