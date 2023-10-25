package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
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
        PrintWriter pw = null;
        BufferedWriter bw = null;
        try {
            pw = new PrintWriter(ApplicationFramework.getInstance().settingsPath);
            bw = new BufferedWriter(pw);
            bw.write("isDarktheme="+!ApplicationFramework.getInstance().isDarkTheme());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if (pw != null) {
                pw.close();
            }
        }
        //obavestiti korisnika da mora da restartuje applikaciju da bi se promenila tema
    }
}
