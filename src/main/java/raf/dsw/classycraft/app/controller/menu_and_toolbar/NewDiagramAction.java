package main.java.raf.dsw.classycraft.app.controller.menu_and_toolbar;

import main.java.raf.dsw.classycraft.app.controller.AbstractClassyAction;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
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
import main.java.raf.dsw.classycraft.app.model.repo.factory.DiagramFactory;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.*;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;
import main.java.raf.dsw.classycraft.app.serializer.JacksonSerializer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;

public class NewDiagramAction extends AbstractClassyAction {
    public NewDiagramAction() {
        putValue(SMALL_ICON, loadIcon("/images/icons/add_diagram.png"));
        putValue(NAME, "New diagram");
        putValue(SHORT_DESCRIPTION, "New diagram");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (MainFrame.getInstance().getClassyTree().getSelectedNode() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.NO_SELECTED_NODE);
            return;
        }
        ClassyTreeItem selectedNode = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (!(selectedNode.getClassyNode() instanceof Package)) {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.DIAGRAM_CAN_ONLY_BE_ADDED_TO_PACKAGE);
            return;
        }

        Object[] options = new Object[]{"Import from library", "Create new"};
        String result = (String) JOptionPane.showInputDialog(
                MainFrame.getInstance(),
                "Do you want to create a new diagram or import one form the library?",
                "New diagram",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                "Create new");
        if (result.equals("Create new")) {
            DiagramFactory diagramFactory = new DiagramFactory();
            ClassyNode classyNode = diagramFactory.classyNode((ClassyNodeComposite) selectedNode.getClassyNode());
            MainFrame.getInstance().getClassyTree().addChild(selectedNode, classyNode);
            return;
        }
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

        JacksonSerializer jacksonSerializer = new JacksonSerializer();
        Diagram fromTemplate = jacksonSerializer.openDiagram(path);

        fromTemplate.setParent((ClassyNodeComposite) MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode());
        MainFrame.getInstance().getClassyTree().addChild(MainFrame.getInstance().getClassyTree().getSelectedNode(), fromTemplate);

        DiagramView dv = new DiagramView(fromTemplate);
        MainFrame.getInstance().getDiagramViews().add(dv);

        for (ClassyNode diagramElement : fromTemplate.getChildren()) {
            diagramElement.setParent(fromTemplate);
            MainFrame.getInstance().getClassyTree().addChild(MainFrame.getInstance().getClassyTree().getNode(fromTemplate), diagramElement);

            if (diagramElement instanceof Connection) {
                for (ClassyNode c : fromTemplate.getChildren()) {
                    if (c instanceof Connection)
                        continue;
                    if (c.getName().equals(((Connection) diagramElement).getFromName())) {
                        ((Connection) diagramElement).setFrom((InterClass) c);
                    }
                    if (c.getName().equals(((Connection) diagramElement).getToName())) {
                        ((Connection) diagramElement).setTo((InterClass) c);
                    }
                }
            }
            if (diagramElement instanceof Klasa) {
                dv.getElementPainters().add(new ClassPainter((Klasa) diagramElement));
            }
            if (diagramElement instanceof Enum) {
                dv.getElementPainters().add(new EnumPainter((Enum) diagramElement));
            }
            if (diagramElement instanceof Interface) {
                dv.getElementPainters().add(new InterfacePainter((Interface) diagramElement));
            }
            if (diagramElement instanceof Aggregation) {
                dv.getElementPainters().add(new AggregationPainter((Connection) diagramElement));
            }
            if (diagramElement instanceof Composition) {
                dv.getElementPainters().add(new CompositionPainter((Connection) diagramElement));
            }
            if (diagramElement instanceof Generalisation) {
                dv.getElementPainters().add(new GeneralisationPainter((Generalisation) diagramElement));
            }
            if (diagramElement instanceof Dependency) {
                dv.getElementPainters().add(new DependencyPainter((Dependency) diagramElement));
            }
        }

    }


}
