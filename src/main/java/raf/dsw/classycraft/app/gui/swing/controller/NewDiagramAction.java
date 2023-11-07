package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;

import java.awt.event.ActionEvent;

public class NewDiagramAction extends AbstractClassyAction{
    public NewDiagramAction() {
        putValue(SMALL_ICON,  loadIcon("/images/icons/add_diagram.png"));
        putValue(NAME, "New diagram");
        putValue(SHORT_DESCRIPTION, "New diagram");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selectedNode = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (!(selectedNode.getClassyNode() instanceof Project || selectedNode.getClassyNode() instanceof Package)) {
            return;
            //TODO ERROR
        }
        ClassyNode classyNode;
        int i = 0;
        while (true) {
            classyNode = new Diagram(ApplicationFramework.getInstance().getClassyRepository().getProjectExplorer(), "diagram  "+i);
            if (!((ClassyNodeComposite) selectedNode.getClassyNode()).getChildren().contains(classyNode)) {
                ((ClassyNodeComposite) selectedNode.getClassyNode()).getChildren().add(classyNode);
                break;
            }
            i++;
        }
        MainFrame.getInstance().getClassyTree().addChild(selectedNode, new ClassyTreeItem(classyNode));
    }
}
