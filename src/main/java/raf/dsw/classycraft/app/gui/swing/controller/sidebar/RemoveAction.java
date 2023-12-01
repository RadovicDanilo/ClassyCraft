package main.java.raf.dsw.classycraft.app.gui.swing.controller.sidebar;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class RemoveAction extends AbstractClassyAction {
    public RemoveAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/remove.png"));
        putValue(NAME, "remove");
        putValue(SHORT_DESCRIPTION, "remove");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().getStateManager().setRemoveState();
    }
}
