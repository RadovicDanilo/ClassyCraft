package main.java.raf.dsw.classycraft.app.command.implementation;

import main.java.raf.dsw.classycraft.app.command.AbstractCommand;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.ElementPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.painter.interclass_painter.InterClassPainter;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;

public class DrawElementCommand extends AbstractCommand {
    private final ElementPainter elementPainter;

    public DrawElementCommand(ElementPainter elementPainter) {
        this.elementPainter = elementPainter;
    }

    @Override
    public void doCommand() {
        elementPainter.addElement();
        getDiagramView().getElementPainters().add(elementPainter);
    }

    @Override
    public void undoCommand() {
        ClassyTreeItem classyTreeItem = MainFrame.getInstance().getClassyTree().getNode(elementPainter.getDiagramElement());
        MainFrame.getInstance().getClassyTree().removeNode(classyTreeItem);
        getDiagramView().getElementPainters().remove(elementPainter);
    }
}
