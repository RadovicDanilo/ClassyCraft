package main.java.raf.dsw.classycraft.app.command;

import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramScrollPane;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;

public abstract class AbstractCommand {
    private final DiagramView diagramView = ((DiagramScrollPane) MainFrame.getInstance().getPackageView().getTabbedPane().getSelectedComponent()).getDiagramView();

    public abstract void doCommand();

    public abstract void undoCommand();

    public DiagramView getDiagramView() {
        return diagramView;
    }
}
