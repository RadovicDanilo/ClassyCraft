package main.java.raf.dsw.classycraft.app.controller.sidebar.draw_connection;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class DrawDependencyAction extends AbstractClassyAction {
    public DrawDependencyAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/dep.png"));
        putValue(NAME, "draw dependency");
        putValue(SHORT_DESCRIPTION, "draw dependency");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startDrawDependencyState();

    }
}

