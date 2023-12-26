package main.java.raf.dsw.classycraft.app.controller.menu_and_toolbar;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.InterClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExportProjectAsJavaCodeAction extends AbstractClassyAction {
    public ExportProjectAsJavaCodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/export_code.png"));
        putValue(NAME, "Export as java code");
        putValue(SHORT_DESCRIPTION, "Export as java code");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.getInstance().getClassyTree().getSelectedNode() == null || !(MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Project)) {
            //TODO SYSTEM EVENT
            return;
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String path = "";
        int returnVal = chooser.showOpenDialog(MainFrame.getInstance());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().getPath();
            System.out.println(path);
        }
        Project project = (Project) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
        initialPath = path;
        export(project, path);
    }
    String initialPath = "";
    public void export(ClassyNode classyNode, String path) {
        if (classyNode instanceof ClassyNodeComposite) {
            path += "/" + classyNode.getName();
            int i = 1;
            if (Files.exists(Paths.get(path))) {
                path = path + " (" + i + ")";
            }
            while (Files.exists(Paths.get(path))) {
                path = path.replace("(" + i + ")","");
                i++;
                path = path + "(" + i + ")";
            }
            System.out.println(path);
            new File(path).mkdirs();//TODO projekti se pravi na disku C napraviti da se pravi u dokumentima i staviti napomenu za to u read me.
            for (ClassyNode child : ((ClassyNodeComposite) classyNode).getChildren()) {
                export(child, path);
            }
            return;
        }
        if(classyNode instanceof Connection)
            return;
        String packPath = path.replace(initialPath,"");
        ((InterClass)classyNode).exportAsCode(path, packPath);

    }

}
