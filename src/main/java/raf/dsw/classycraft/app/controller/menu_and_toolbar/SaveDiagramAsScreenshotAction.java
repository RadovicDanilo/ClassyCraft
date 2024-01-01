package main.java.raf.dsw.classycraft.app.controller.menu_and_toolbar;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;

import java.awt.event.ActionEvent;

public class SaveDiagramAsScreenshotAction extends AbstractClassyAction {
    public SaveDiagramAsScreenshotAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/screenshot.png"));
        putValue(NAME, "Save diagram as screenshot");
        putValue(SHORT_DESCRIPTION, "Save diagram as screenshot");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO na kraju dodati u readme da se cuva otvoren diagram a ne onaj koji je selektovan u stablu
        if (MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent() == null || ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView() == null) {
            //TODO SYSTEM EVENT
            //OVO CU JA NA KRAJU, NE MORAS DA DIRAS NISTA VAN OVE KLASE
            return;
        }
        Diagram diagram = ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView().getDiagram();


    }
}
