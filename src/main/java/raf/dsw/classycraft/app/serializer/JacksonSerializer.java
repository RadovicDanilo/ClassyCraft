package main.java.raf.dsw.classycraft.app.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JacksonSerializer {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public void setProjectPath(Project project) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String path = "";
        int returnVal = chooser.showOpenDialog(MainFrame.getInstance());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().getPath() + "\\" + project.getName() + ".json";
        }
        if(path.equals(""))
            return;
        int i = 1;
        if (Files.exists(Paths.get(path))) {
            path = path.substring(0, path.length() - 5) + " (" + i + ").json";
        }
        while (Files.exists(Paths.get(path))) {
            path = path.replace("(" + i + ")", "(" + i + 1 + ")");
            i++;
        }
        project.setResourcePath(path);
    }

    public void save(Project project) {
        ClassyNode classyNode = MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(project.getResourcePath()), classyNode);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Project load(String path) {
        System.out.println(path);
        try {
            return objectMapper.readValue(new File(path), Project.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
