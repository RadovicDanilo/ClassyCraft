package main.java.raf.dsw.classycraft.app.controller.sidebar.dc;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class DrawGeneralisationAction extends AbstractClassyAction {
    public DrawGeneralisationAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/gener.png"));
        putValue(NAME, "draw generalisation");
        putValue(SHORT_DESCRIPTION, "draw generalisation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startDrawGeneralisationState();

    }
}
