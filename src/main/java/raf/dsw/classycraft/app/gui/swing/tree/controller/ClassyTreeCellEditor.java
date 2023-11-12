package main.java.raf.dsw.classycraft.app.gui.swing.tree.controller;

import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.model.message.Message;
import main.java.raf.dsw.classycraft.app.model.message.MessageType;
import main.java.raf.dsw.classycraft.app.model.message.SystemEvent;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;

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
    private JTextField edit = null;

    public ClassyTreeCellEditor(JTree jTree, DefaultTreeCellRenderer defaultTreeCellRenderer) {
        super(jTree, defaultTreeCellRenderer);
    }
    public Component getTreeCellEditorComponent(JTree jTree, Object clickedOn, boolean isSelected, boolean expanded, boolean leaf, int row) {
        super.getTreeCellEditorComponent(jTree,clickedOn,isSelected,expanded,leaf,row);
        this.clickedOn =clickedOn;
        this.edit=new JTextField(clickedOn.toString());
        this.edit.addActionListener(this);
        return edit;
    }
    public boolean isCellEditable(EventObject eventObject) {
        if (eventObject instanceof MouseEvent)
            if (((MouseEvent)eventObject).getClickCount()==3){
                return true;
            }
        return false;
    }
    public void actionPerformed(ActionEvent e){

        if (!(clickedOn instanceof ClassyTreeItem))
            return;

        ClassyTreeItem clicked = (ClassyTreeItem) clickedOn;
        clicked.setName(e.getActionCommand());

        ClassyNode node = clicked.getClassyNode();
        if(node instanceof Package) ((Package)node).notifySubscribers(new Message(SystemEvent.NODE_NAME_CHANGED, MessageType.INFO, ""));
        if(node instanceof Diagram) ((Package)node.getParent()).notifySubscribers(new Message(SystemEvent.NODE_NAME_CHANGED, MessageType.INFO, ""));
    }
}
