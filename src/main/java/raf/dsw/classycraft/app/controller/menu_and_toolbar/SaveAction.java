package main.java.raf.dsw.classycraft.app.controller.menu_and_toolbar;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        if(project.getResourcePath() == null){
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            String path = "";
            String fileName = project.getName() + ".json";
            int returnVal = chooser.showOpenDialog(MainFrame.getInstance());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                path = chooser.getSelectedFile().getPath();
            }
            path += "/";
            int i = 1;
            if (Files.exists(Paths.get(path + fileName))) {
                fileName = fileName.substring(0, fileName.length() - 5) + " (" + i + ").json";
            }
            while (Files.exists(Paths.get(path + fileName))) {
                fileName = fileName.replace("(" + i + ")", "(" + i + 1 + ")");
                i++;
            }
            new File(path).mkdirs();
            project.setResourcePath(path + fileName);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ClassyNode classyNode = MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(project.getResourcePath()), classyNode);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
