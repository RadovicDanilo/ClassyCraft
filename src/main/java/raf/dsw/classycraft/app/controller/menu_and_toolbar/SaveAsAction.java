package main.java.raf.dsw.classycraft.app.controller.menu_and_toolbar;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.serializer.JacksonSerializer;

import java.awt.event.ActionEvent;

public class SaveAsAction extends AbstractClassyAction {

    public SaveAsAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/save.png"));
        putValue(NAME, "Save as");
        putValue(SHORT_DESCRIPTION, "Save as");
        disable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyNode project = MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();

        while(!(project instanceof Project)){
            project = project.getParent();
        }
        JacksonSerializer jacksonSerializer = new JacksonSerializer();
        jacksonSerializer.setProjectPath((Project) project);
        jacksonSerializer.saveProject((Project) project);

        ((Project) project).setChanged(false);
        MainFrame.getInstance().getActionManager().getSaveAction().disable();
    }

    public void enable() {
        super.setEnabled(true);
    }

    public void disable() {
        super.setEnabled(false);
    }
}
