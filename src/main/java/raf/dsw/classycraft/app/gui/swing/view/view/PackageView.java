package main.java.raf.dsw.classycraft.app.gui.swing.view.view;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.observer.ISubscriber;
import main.java.raf.dsw.classycraft.app.observer.notifications.PackageViewEvent;
import main.java.raf.dsw.classycraft.app.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

public class PackageView extends JPanel implements ISubscriber {
    private final StateManager stateManager;
    private Package selectedPackage;
    private JLabel lbProjectName;
    private JTabbedPane tabbedPane;

    public PackageView() {
        super(new BorderLayout());
        stateManager = new StateManager();
        tabbedPane = new JTabbedPane();
        tabbedPane.addChangeListener(new MyTabChangeListener());
        lbProjectName = new JLabel();
        this.add(lbProjectName, BorderLayout.NORTH);
        this.add(tabbedPane, BorderLayout.CENTER);

    }

    public void openTabs(List<Diagram> diagrams, Package selectedPackage) {
        for (Component dv : tabbedPane.getComponents()) {
            MainFrame.getInstance().addDiagramView(((DiagramScrollPane) dv).getDiagramView());
        }
        tabbedPane.removeAll();
        if (selectedPackage != null) {
            selectedPackage.closedPane();
        }
        this.selectedPackage = selectedPackage;
        if (selectedPackage != null) {
            selectedPackage.addSubscriber(this);
        }
        ClassyNode project = selectedPackage;
        while (!(project instanceof Project)) {
            project = project.getParent();
        }

        if (diagrams.size() == 0)
            return;

        lbProjectName.setText("<html>" + project.getName() + "<br>Autor: " + ((Project) project).getAuthor() + "<html>");
        lbProjectName.setFont(new Font("Calibri", Font.BOLD, 14));

        for (Diagram diagram : diagrams) {
            DiagramView dv = new DiagramView(diagram);
            for (int i = 0; i < MainFrame.getInstance().getDiagramViews().size(); i++) {
                if (dv.equals(MainFrame.getInstance().getDiagramViews().get(i))) {
                    dv = MainFrame.getInstance().getDiagramViews().get(i);
                    break;
                }
            }
            DiagramScrollPane diagramScrollPane = new DiagramScrollPane(dv);
            tabbedPane.addTab(diagram.getName(), diagramScrollPane);
        }

    }

    @Override
    public void update(Object notification) {
        if (!(notification instanceof PackageViewEvent)) {
            return;
        }
        switch ((PackageViewEvent) notification) {
            case ADD_DIAGRAM:
            case REMOVE_DIAGRAM:
            case RENAME_DIAGRAM:
                reloadPackage();
                break;
            case REMOVE_ALL:
                removePackageOrProject();
                break;
            case RENAME_PROJECT:
            case CHANGE_AUTHOR:
                updateProject();
                break;
        }
    }

    public void reloadPackage() {
        List<Diagram> diagrams = new ArrayList<>();
        for (ClassyNode diagram : this.getSelectedPackage().getChildren()) {
            if (diagram instanceof Diagram) diagrams.add((Diagram) diagram);
        }
        this.openTabs(diagrams, this.getSelectedPackage());
    }

    public void removePackageOrProject() {
        super.removeAll();
        super.revalidate();
        super.repaint();
        super.setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        lbProjectName = new JLabel();
        this.add(lbProjectName, BorderLayout.NORTH);
        this.add(tabbedPane, BorderLayout.CENTER);
    }

    public void updateProject() {
        ClassyNode project = selectedPackage;

        while (!(project instanceof Project)) {
            project = project.getParent();
        }

        lbProjectName.setText("<html>" + project.getName() + "<br>Autor: " + ((Project) project).getAuthor() + "<html>");
        lbProjectName.setFont(new Font("Calibri", Font.BOLD, 14));
    }


    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }


    public Package getSelectedPackage() {
        return selectedPackage;
    }


    public void mousePressed(MouseEvent e, DiagramView diagramView) {
        this.stateManager.getCurrentState().mousePressed(e, diagramView);
    }

    public void mouseRelease(MouseEvent e, DiagramView diagramView) {
        this.stateManager.getCurrentState().mouseRelease(e, diagramView);
    }

    public void mouseDragged(MouseEvent e, DiagramView diagramView) {
        this.stateManager.getCurrentState().mouseDragged(e, diagramView);
    }

    public void mouseWheelMoved(MouseWheelEvent e, DiagramView diagramView) {
        this.stateManager.getCurrentState().mouseWheelMoved(e, diagramView);
    }


    public void startSelectState() {
        stateManager.setSelectState();
    }

    public void startMultiSelectState() {
        stateManager.setMultiSelectState();
    }

    public void startRemoveState() {
        stateManager.setRemoveState();
    }

    public void startDrawClassState() {
        stateManager.setDrawClassState();

    }

    public void startDrawInterfaceState() {
        stateManager.setDrawInterfaceState();

    }

    public void startDrawEnumState() {
        stateManager.setDrawEnumState();

    }

    public void startDrawFieldState() {
        stateManager.setDrawFieldState();

    }

    public void startDrawMethodState() {
        stateManager.setDrawMethodState();

    }

    public void startEditContentState() {
        stateManager.setEditContentState();

    }

    public void startDrawAggregationState() {
        stateManager.setDrawAggregationState();

    }

    public void startDrawGeneralisationState() {
        stateManager.setDrawGeneralisationState();

    }

    public void startDrawCompositionState() {
        stateManager.setDrawCompositionState();

    }

    public void startDrawDependencyState() {
        stateManager.setDrawDependencyState();

    }

    public void startZoomToFitState() {
        stateManager.setZoomToFitState();

    }


    public void startDuplicateAction() {
        stateManager.setDuplicateState();
    }

    public StateManager getStateManager() {
        return stateManager;
    }
}
