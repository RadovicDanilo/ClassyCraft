package main.java.raf.dsw.classycraft.app.controller.menu_and_toolbar;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UndoAction extends AbstractClassyAction {
    public UndoAction() {
        putValue(SMALL_ICON, new ImageIcon(GrayFilter.createDisabledImage(((ImageIcon) loadIcon("/images/icons/undo.png")).getImage())));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DiagramView diagramView = ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView();
        diagramView.getCommandManager().undoCommand();
    }

    public void enable() {
        putValue(SMALL_ICON, loadIcon("/images/icons/undo.png"));
    }

    public void disable() {
        putValue(SMALL_ICON, new ImageIcon(GrayFilter.createDisabledImage(((ImageIcon) loadIcon("/images/icons/undo.png")).getImage())));
    }
}
