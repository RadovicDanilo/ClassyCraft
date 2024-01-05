package main.java.raf.dsw.classycraft.app.controller.menu_and_toolbar;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;
import main.java.raf.dsw.classycraft.app.serializer.JacksonSerializer;

import java.awt.event.ActionEvent;

public class SaveDiagramAsTemplateAction extends AbstractClassyAction {
    public SaveDiagramAsTemplateAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/template.png"));
        putValue(NAME, "Save diagram as template");
        putValue(SHORT_DESCRIPTION, "Save diagram as template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.getInstance().getClassyTree().getSelectedNode() == null || !(MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Diagram)) {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.NO_DIAGRAM_SELECTED);
        }
        Diagram diagram = (Diagram) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
        JacksonSerializer jacksonSerializer = new JacksonSerializer();
        jacksonSerializer.saveDiagramTemplate(diagram);
    }
}
