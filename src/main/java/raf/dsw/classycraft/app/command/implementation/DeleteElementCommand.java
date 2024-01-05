package main.java.raf.dsw.classycraft.app.command.implementation;

import main.java.raf.dsw.classycraft.app.command.AbstractCommand;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;

import java.util.ArrayList;

public class DeleteElementCommand extends AbstractCommand {
    private final ArrayList<ElementPainter> elementPainters;

    public DeleteElementCommand(ArrayList<ElementPainter> elementPainters) {
        this.elementPainters = elementPainters;
    }

    @Override
    public void doCommand() {
        for (ElementPainter elementPainter : elementPainters) {
            ClassyTreeItem classyTreeItem = MainFrame.getInstance().getClassyTree().getNode(elementPainter.getDiagramElement());
            MainFrame.getInstance().getClassyTree().removeNode(classyTreeItem);
            getDiagramView().getElementPainters().remove(elementPainter);
        }
    }

    @Override
    public void undoCommand() {
        for (ElementPainter elementPainter : elementPainters) {
            elementPainter.addElement();
            getDiagramView().getElementPainters().add(elementPainter);
        }
    }
}
