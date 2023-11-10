package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import main.java.raf.dsw.classycraft.app.model.message.SystemEvent;
import main.java.raf.dsw.classycraft.app.model.repo.ClassyRepositoryImplementation;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.NodeType;
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
        if(!(selectedNode.getClassyNode() instanceof Project || selectedNode.getClassyNode() instanceof Package)){
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.CANNOT_ADD_DIAGRAM_TO_ROOT_OR_DIAGRAM);
            return;
        }
        ClassyNode classyNode;
        int i = 0;
        while(true){
            classyNode = ((ClassyRepositoryImplementation)ApplicationFramework.getInstance().getClassyRepository()).getClassyNodeFactory().classyNode(NodeType.DIAGRAM, "diagram " + i, selectedNode.getClassyNode());
            if(!((ClassyNodeComposite) selectedNode.getClassyNode()).getChildren().contains(classyNode)){
                ApplicationFramework.getInstance().getClassyRepository().addChild(classyNode);
                break;
            }
            i++;
        }
        MainFrame.getInstance().getClassyTree().addChild(selectedNode,new ClassyTreeItem(classyNode));
        //TODO ISTO KAO I ONA DRUGA STVAR
    }
}
