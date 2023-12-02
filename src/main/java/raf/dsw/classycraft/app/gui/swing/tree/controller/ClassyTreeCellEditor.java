package main.java.raf.dsw.classycraft.app.gui.swing.tree.controller;

import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.model.observer.notifications.PackageViewEvent;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;

public class ClassyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {
    private Object clickedOn = null;

    public ClassyTreeCellEditor(JTree jTree, DefaultTreeCellRenderer defaultTreeCellRenderer) {
        super(jTree, defaultTreeCellRenderer);
    }

    public Component getTreeCellEditorComponent(JTree jTree, Object clickedOn, boolean isSelected, boolean expanded, boolean leaf, int row) {
        super.getTreeCellEditorComponent(jTree, clickedOn, isSelected, expanded, leaf, row);
        this.clickedOn = clickedOn;
        JTextField edit = new JTextField(clickedOn.toString());
        edit.addActionListener(this);
        return edit;
    }

    public boolean isCellEditable(EventObject eventObject) {
        if (eventObject instanceof MouseEvent)
            return ((MouseEvent) eventObject).getClickCount() == 3;
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        if (!(clickedOn instanceof ClassyTreeItem))
            return;
        ClassyTreeItem clicked = (ClassyTreeItem) clickedOn;
        clicked.setName(e.getActionCommand());

        if (clicked.getClassyNode() instanceof Diagram) {
            ((Package) clicked.getClassyNode().getParent()).notifySubscribers(PackageViewEvent.RENAME_DIAGRAM);
        } else if (clicked.getClassyNode() instanceof Project) {
            changeProjectNameUpdate((ArrayList<ClassyNode>) ((Project) clicked.getClassyNode()).getChildren());
        }
    }

    public void changeProjectNameUpdate(ArrayList<ClassyNode> children) {
        for (ClassyNode classyNode : children) {
            if (classyNode instanceof Package) {
                ((Package) classyNode).notifySubscribers(PackageViewEvent.RENAME_PROJECT);
                changeProjectNameUpdate((ArrayList<ClassyNode>) ((Package) classyNode).getChildren());
            }
        }
    }
}
