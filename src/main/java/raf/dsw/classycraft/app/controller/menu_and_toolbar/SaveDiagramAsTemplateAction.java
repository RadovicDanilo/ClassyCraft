package main.java.raf.dsw.classycraft.app.controller.menu_and_toolbar;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;

import java.awt.event.ActionEvent;

public class SaveDiagramAsTemplateAction extends AbstractClassyAction {
    public SaveDiagramAsTemplateAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/template.png"));
        putValue(NAME, "Save diagram as template");
        putValue(SHORT_DESCRIPTION, "Save diagram as template");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getClassyTree().getSelectedNode() == null || !(MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Diagram))
            return;
        Diagram diagram = (Diagram) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
        //TODO implement
    }
}
