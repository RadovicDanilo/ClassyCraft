package main.java.raf.dsw.classycraft.app.gui.swing.tree.controller;

import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class ClassyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {
    private Object clickedOn = null;

    public ClassyTreeCellEditor(JTree jTree, DefaultTreeCellRenderer defaultTreeCellRenderer) {
        super(jTree, defaultTreeCellRenderer);
    }
    public Component getTreeCellEditorComponent(JTree jTree, Object clickedOn, boolean isSelected, boolean expanded, boolean leaf, int row) {
        super.getTreeCellEditorComponent(jTree,clickedOn,isSelected,expanded,leaf,row);
        this.clickedOn =clickedOn;
        JTextField edit = new JTextField(clickedOn.toString());
        edit.addActionListener(this);
        return edit;
    }
    public boolean isCellEditable(EventObject eventObject) {
        if (eventObject instanceof MouseEvent)
            return ((MouseEvent) eventObject).getClickCount() == 3;
        return false;
    }
    public void actionPerformed(ActionEvent e){

        if (!(clickedOn instanceof ClassyTreeItem))
            return;

        ClassyTreeItem clicked = (ClassyTreeItem) clickedOn;
        clicked.setName(e.getActionCommand());

    }
}
