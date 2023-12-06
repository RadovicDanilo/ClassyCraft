package main.java.raf.dsw.classycraft.app.controller.sidebar;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class MultiSelectStateAction extends AbstractClassyAction {
    public MultiSelectStateAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/multi.png"));//TODO ICON
        putValue(NAME, "multi select");
        putValue(SHORT_DESCRIPTION, "multi select");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().getStateManager().setMultiSelectState();
    }
}
