package main.java.raf.dsw.classycraft.app.controller.menu_and_toolbar;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.serializer.JacksonSerializer;

import java.awt.event.ActionEvent;

public class SaveAction extends AbstractClassyAction {

    public SaveAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/save.png"));
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Save");
        disable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyNode project = MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
        while (!(project instanceof Project)) {
            project = project.getParent();
        }

        JacksonSerializer jacksonSerializer = new JacksonSerializer();
        if (((Project) project).getResourcePath() == null) {
            jacksonSerializer.setProjectPath((Project) project);
        }
        jacksonSerializer.saveProject((Project) project);
        ((Project) project).setChanged(false);
        disable();
    }

    public void enable() {
        super.setEnabled(true);
    }

    public void disable() {
        super.setEnabled(false);
    }
}
