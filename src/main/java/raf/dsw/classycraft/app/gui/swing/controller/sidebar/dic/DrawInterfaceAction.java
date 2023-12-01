package main.java.raf.dsw.classycraft.app.gui.swing.controller.sidebar.dic;

import main.java.raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class DrawInterfaceAction extends AbstractClassyAction {
    public DrawInterfaceAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/i.png"));
        putValue(NAME, "draw interface");
        putValue(SHORT_DESCRIPTION, "draw interface");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().getStateManager().setDrawInterfaceState();

    }
}
