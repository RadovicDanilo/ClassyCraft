package main.java.raf.dsw.classycraft.app.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.BAD_PATH);
            return;
        }
        int i = 1;
        if (Files.exists(Paths.get(path))) {
            path = path.substring(0, path.length() - 5) + " (" + i + ").json";
        }
        while (Files.exists(Paths.get(path))) {
            int x = i + 1;
            path = path.replace("(" + i + ")", "(" + x + ")");
            i++;
        }
        project.setResourcePath(path);
    }

    public void saveProject(Project project) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(project.getResourcePath()), project);
        } catch (IOException ex) {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.BAD_PATH);
        }
    }

    public Project openProject() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON FILES", "json");
        chooser.setFileFilter(filter);
        String path = "";
        int returnVal = chooser.showOpenDialog(MainFrame.getInstance());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().getPath();
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.BAD_PATH);

        }
        try {
            return objectMapper.readValue(new File(path), Project.class);
        } catch (IOException e) {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.BAD_PATH);
        }
        return null;
    }

    public void saveDiagramTemplate(Diagram diagram) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String path = "";
        int returnVal = chooser.showOpenDialog(MainFrame.getInstance());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().getPath() + "\\" + diagram.getName() + ".json";
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.BAD_PATH);
        }
        int i = 1;
        if (Files.exists(Paths.get(path))) {
            path = path.substring(0, path.length() - 5) + " (" + i + ").json";
        }
        while (Files.exists(Paths.get(path))) {
            int x = i + 1;
            path = path.replace("(" + i + ")", "(" + x + ")");
            i++;
        }
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), diagram);
        } catch (IOException ex) {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.BAD_PATH);
        }
    }

    public Diagram openDiagram(String path) {
        try {
            return objectMapper.readValue(new File(path), Diagram.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
