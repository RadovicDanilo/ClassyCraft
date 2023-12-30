package main.java.raf.dsw.classycraft.app.controller.menu_and_toolbar;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;
import main.java.raf.dsw.classycraft.app.serializer.JacksonSerializer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveAsAction extends AbstractClassyAction {

    public SaveAsAction() {
        putValue(SMALL_ICON, new ImageIcon(GrayFilter.createDisabledImage(((ImageIcon) loadIcon("/images/icons/save.png")).getImage())));
        putValue(NAME, "Save as");
        putValue(SHORT_DESCRIPTION, "Save as");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.getInstance().getClassyTree().getSelectedNode() == null) {
            //TODO SYSTEM EVENT
            return;
        }
        ClassyNode project = MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
        if(project instanceof ProjectExplorer) {
            //TODO SYSTEM EVENT
            return;
        }
        while(!(project instanceof Project)){
            project = project.getParent();
        }
        JacksonSerializer jacksonSerializer = new JacksonSerializer();
        jacksonSerializer.setProjectPath((Project) project);
        jacksonSerializer.save((Project) project);
        ((Project) project).setChanged(false);
        MainFrame.getInstance().getActionManager().getSaveAction().disable();
    }

    public void enable() {
        putValue(SMALL_ICON, loadIcon("/images/icons/save.png"));
    }

    public void disable() {
        putValue(SMALL_ICON, new ImageIcon(GrayFilter.createDisabledImage(((ImageIcon) loadIcon("/images/icons/save.png")).getImage())));
    }
}
