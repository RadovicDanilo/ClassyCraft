package main.java.raf.dsw.classycraft.app.controller.menu_and_toolbar;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.connection_painter.AggregationPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.connection_painter.CompositionPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.connection_painter.DependencyPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.connection_painter.GeneralisationPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.interclass_painter.ClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.interclass_painter.EnumPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.interclass_painter.InterfacePainter;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.*;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.serializer.JacksonSerializer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.MutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OpenAction extends AbstractClassyAction {
    public OpenAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/open.png"));
        putValue(NAME, "Open");
        putValue(SHORT_DESCRIPTION, "Open");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON FILES", "json");
        chooser.setFileFilter(filter);
        String path = "";
        int returnVal = chooser.showOpenDialog(MainFrame.getInstance());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().getPath();
        }
        if (path.equals(""))
            return;

        JacksonSerializer jacksonSerializer = new JacksonSerializer();
        Project project = jacksonSerializer.load(path);
        ProjectExplorer root = (ProjectExplorer) ApplicationFramework.getInstance().getClassyRepository().getRoot();
        ClassyTreeItem treeRoot = MainFrame.getInstance().getClassyTree().getRoot();
        for (ClassyNode c : root.getChildren()) {
            if (c.equals(project)) {
                //TODO SYS EVENT
                return;
            }
        }
        fix(treeRoot,root, project);

        //add commands in tree view to fix renaming issues
    }

    public void fix(ClassyTreeItem treeRoot, ClassyNodeComposite parent, ClassyNode child) {

        child.setParent(parent);

        ClassyTreeItem treeChild = new ClassyTreeItem(child);
        treeRoot.add(treeChild);

        if (child instanceof ClassyNodeComposite) {
            if(child instanceof Diagram){
                MainFrame.getInstance().getDiagramViews().add(new DiagramView((Diagram) child));
            }
            for (ClassyNode c : ((ClassyNodeComposite) child).getChildren()) {
                fix(treeChild, (ClassyNodeComposite) child, c);
            }
            return;
        }
        if (child instanceof InterClass) {
            ((InterClass) child).generatePoints();
        }
        if (child instanceof Connection) {
            for (ClassyNode c : parent.getChildren()) {
                if (c instanceof Connection)
                    continue;
                if (c.getName().equals(((Connection) child).getFromName())) {
                    ((Connection) child).setFrom((InterClass) c);
                }
                if (c.getName().equals(((Connection) child).getToName())) {
                    ((Connection) child).setTo((InterClass) c);
                }
            }
        }
        for(DiagramView diagramView: MainFrame.getInstance().getDiagramViews()){
            if(!diagramView.getDiagram().equals(parent)){
                continue;
            }
            if(child instanceof Klasa){
                diagramView.getElementPainters().add(new ClassPainter((Klasa) child));
            }
            if(child instanceof Enum){
                diagramView.getElementPainters().add(new EnumPainter((Enum) child));
            }
            if(child instanceof Interface){
                diagramView.getElementPainters().add(new InterfacePainter((Interface) child));
            }
            if(child instanceof Aggregation){
                diagramView.getElementPainters().add(new AggregationPainter((Connection) child));
            }
            if(child instanceof Composition){
                diagramView.getElementPainters().add(new CompositionPainter((Connection) child));
            }
            if(child instanceof Generalisation){
                diagramView.getElementPainters().add(new GeneralisationPainter((Generalisation) child));
            }
            if(child instanceof Dependency){
                diagramView.getElementPainters().add(new DependencyPainter((Dependency) child));
            }
        }
    }
}
