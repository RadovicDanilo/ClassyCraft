package main.java.raf.dsw.classycraft.app.command;

import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private final List<AbstractCommand> commands = new ArrayList<>();
    private int currentCommand = 0;

    public void addCommand(AbstractCommand command) {
        while (currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        doCommand();
    }

    public void doCommand() {
        if (currentCommand < commands.size()) {
            commands.get(currentCommand++).doCommand();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getClassyTree().getTreeView());
            MainFrame.getInstance().getActionManager().getUndoAction().enable();
        }
        if (currentCommand == commands.size()) {
            MainFrame.getInstance().getActionManager().getRedoAction().disable();
        }
    }

    public void undoCommand() {
        if (currentCommand > 0) {
            MainFrame.getInstance().getActionManager().getRedoAction().enable();
            commands.get(--currentCommand).undoCommand();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getClassyTree().getTreeView());
        }
        if (currentCommand == 0) {
            MainFrame.getInstance().getActionManager().getUndoAction().disable();
        }
    }

    public void refresh(){
        if(currentCommand == 0){
            MainFrame.getInstance().getActionManager().getUndoAction().disable();
        }else {
            MainFrame.getInstance().getActionManager().getUndoAction().enable();
        }
        if(currentCommand < commands.size()){
            MainFrame.getInstance().getActionManager().getRedoAction().enable();
        }else {
            MainFrame.getInstance().getActionManager().getRedoAction().disable();
        }
    }

}
