package main.java.raf.dsw.classycraft.app.gui.swing.controller;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import main.java.raf.dsw.classycraft.app.model.message.SystemEvent;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.factory.PackageFactory;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;

import java.awt.event.ActionEvent;

public class NewPackageAction extends AbstractClassyAction{
    public NewPackageAction() {
        putValue(SMALL_ICON,  loadIcon("/images/icons/add_package.png"));
        putValue(NAME, "New package");
        putValue(SHORT_DESCRIPTION, "New package");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO OVDE VEROVATNO JTABBEDPANE TREBA DA GLEDA JEL IMA PROMENA

        ClassyTreeItem selectedNode = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(!(selectedNode.getClassyNode() instanceof Project || selectedNode.getClassyNode() instanceof Package)){
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.CANNOT_ADD_PACKAGE_TO_ROOT_OR_DIAGRAM);
            return;
        }
        ClassyNode classyNode;
        int i = 0;
        while(true){
            PackageFactory packageFactory = new PackageFactory();
            classyNode = packageFactory.classyNode("package " + i, selectedNode.getClassyNode());
            if(!((ClassyNodeComposite) selectedNode.getClassyNode()).getChildren().contains(classyNode)){
                ApplicationFramework.getInstance().getClassyRepository().addChild(classyNode);
                break;
            }
            i++;
        }
        MainFrame.getInstance().getClassyTree().addChild(selectedNode,classyNode);
    }
}
