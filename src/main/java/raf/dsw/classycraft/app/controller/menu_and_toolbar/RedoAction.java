package main.java.raf.dsw.classycraft.app.controller.menu_and_toolbar;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;

import java.awt.event.ActionEvent;

public class RedoAction extends AbstractClassyAction {
    public RedoAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/redo.png"));
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");
        disable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView diagramView = ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView();
        diagramView.getCommandManager().doCommand();
    }

    public void enable() {
        super.setEnabled(true);
    }

    public void disable() {
        super.setEnabled(false);
    }
}
