package main.java.raf.dsw.classycraft.app;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;

public class Main {
    public static void main(String[] args) {
        ApplicationFramework.getInstance().loadThemeSettings();
        if(ApplicationFramework.getInstance().isDarkTheme())
            FlatDarkLaf.setup();
        else
            FlatLightLaf.setup();
        ApplicationFramework main = ApplicationFramework.getInstance();
        main.initialize();
    }
}