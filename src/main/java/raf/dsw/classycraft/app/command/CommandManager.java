package main.java.raf.dsw.classycraft.app.command;

import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private List<AbstractCommand> commands = new ArrayList<>();
    private int currentCommand = 0;

    public void addCommand(AbstractCommand command){
        while(currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        doCommand();
    }

    public void doCommand(){
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).doCommand();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getClassyTree().getTreeView());
            //ApplicationFramework.getInstance().getGui().enableUndoAction();
        }
        if(currentCommand==commands.size()){
            //ApplicationFramework.getInstance().getGui().disableRedoAction();
        }
    }

    public void undoCommand(){
        if(currentCommand > 0){
            //ApplicationFramework.getInstance().getGui().enableRedoAction();
            commands.get(--currentCommand).undoCommand();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getClassyTree().getTreeView());
        }
        if(currentCommand==0){
            //ApplicationFramework.getInstance().getGui().disableUndoAction();
        }
    }

}
