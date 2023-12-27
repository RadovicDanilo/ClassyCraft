package main.java.raf.dsw.classycraft.app.controller.menu_and_toolbar;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.serializer.JacksonSerializer;

import java.awt.event.ActionEvent;

public class SaveAction extends AbstractClassyAction {
    public SaveAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/save.png"));
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Save");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.getInstance().getClassyTree().getSelectedNode() == null || !(MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Project)) {
            //TODO SYSTEM EVENT
            return;
        }
        Project project = (Project) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
        JacksonSerializer jacksonSerializer = new JacksonSerializer();
        if (project.getResourcePath() == null) {
            jacksonSerializer.setProjectPath(project);
        }
        jacksonSerializer.save(project);
    }
}
