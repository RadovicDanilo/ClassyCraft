package main.java.raf.dsw.classycraft.app.gui.swing.tree.controller;

import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;

import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class ClassyTreeOpenTabsListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()!=2)
            return;
        ClassyTreeView tree = (ClassyTreeView) e.getSource();
        TreePath path = tree.getPathForLocation(e.getX(), e.getY());
        ClassyTreeItem treeItem = null;
        if (path != null) {
            treeItem = (ClassyTreeItem) path.getLastPathComponent();
        }
        ClassyNode node = null;
        if (treeItem != null) {
            node = treeItem.getClassyNode();
        }

        List<Diagram> diagrams = new ArrayList<>();
        Package selectedPackage = null;

        if(node instanceof Package){
            selectedPackage = (Package) node;
            for(ClassyNode diagram: ((Package) node).getChildren()){
                if(diagram instanceof Diagram) diagrams.add((Diagram) diagram);
            }
        }
        if(node instanceof Diagram){
            selectedPackage = (Package) node.getParent();
            diagrams.add((Diagram)node);
        }
        if(!(node instanceof Package || node instanceof Diagram))
            return;
        if(diagrams.size() == 0)
            return;

        MainFrame.getInstance().getPackageView().openTabs(diagrams, selectedPackage);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
