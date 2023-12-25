package main.java.raf.dsw.classycraft.app.controller.sidebar.draw_interclass.draw_class_content;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.awt.event.ActionEvent;

public class EditContentAction extends AbstractClassyAction {
    public EditContentAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/edit.png"));
        putValue(NAME, "edit content");
        putValue(SHORT_DESCRIPTION, "edit content");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startEditContentState();

    }
}
