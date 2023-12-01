package main.java.raf.dsw.classycraft.app.gui.swing.controller.sidebar;

import main.java.raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomInAction extends AbstractClassyAction {
    public ZoomInAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/zoom_in.png"));
        putValue(NAME, "zoom in");
        putValue(SHORT_DESCRIPTION, "zoom in");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().getStateManager().setZoomInState();
    }
}
