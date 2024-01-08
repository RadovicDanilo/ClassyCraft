package main.java.raf.dsw.classycraft.app.gui.swing.view.view;

import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyTabChangeListener implements ChangeListener {
    @Override
    public void stateChanged(ChangeEvent e) {
        if (MainFrame.getInstance().getPackageView().getTabbedPane().getTabCount() == 0) {
            return;
        }
        if (MainFrame.getInstance().getPackageView().getTabbedPane() == null) {
            return;
        }
        if (MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent() == null) {
            return;
        }
        if (((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView() == null) {
            return;
        }
        ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView().getCommandManager().refresh();

    }
}
