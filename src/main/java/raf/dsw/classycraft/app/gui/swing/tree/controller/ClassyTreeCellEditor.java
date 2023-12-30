package main.java.raf.dsw.classycraft.app.gui.swing.tree.controller;

import main.java.raf.dsw.classycraft.app.command.CommandManager;
import main.java.raf.dsw.classycraft.app.command.implementation.EditClassCommand;
import main.java.raf.dsw.classycraft.app.command.implementation.EditEnumCommand;
import main.java.raf.dsw.classycraft.app.command.implementation.EditInterfaceCommand;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;

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
        if (clicked.getClassyNode() instanceof ProjectExplorer) {
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.CANNOT_RENAME_ROOT);
            return;
        }
        if(clicked.getClassyNode() instanceof Connection){
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.CANNOT_RENAME_CONNECTION);
            return;
        }
        CommandManager cm = ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView().getCommandManager();
        if(clicked.getClassyNode() instanceof Klasa) {
            Klasa k = (Klasa) clicked.getClassyNode();
            cm.addCommand(new EditClassCommand(k, e.getActionCommand(), k.getContents()));
            return;
        }
        if(clicked.getClassyNode() instanceof Interface) {
            Interface k = (Interface) clicked.getClassyNode();
            cm.addCommand(new EditInterfaceCommand(k, e.getActionCommand(), k.getMethods()));
            return;
        }
        if(clicked.getClassyNode() instanceof Enum) {
            Enum k = (Enum) clicked.getClassyNode();
            cm.addCommand(new EditEnumCommand(k, e.getActionCommand(), k.getContents()));
            return;
        }
        clicked.setName(e.getActionCommand());
    }


}
