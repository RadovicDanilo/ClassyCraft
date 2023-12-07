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
import java.util.ArrayList;
import java.util.List;

public class PackageView extends JPanel implements ISubscriber {
	private Package selectedPackage;
	private StateManager stateManager;
	private JLabel lbProjectName;
	private JTabbedPane tabbedPane;
	
	public PackageView() {
		super(new BorderLayout());
		stateManager = new StateManager();
		tabbedPane = new JTabbedPane();
		lbProjectName = new JLabel();
		this.add(lbProjectName, BorderLayout.NORTH);
		this.add(tabbedPane, BorderLayout.CENTER);
	}
	
	public void openTabs(List<Diagram> diagrams, Package selectedPackage) {
		for(Component dv : tabbedPane.getComponents()) {
			MainFrame.getInstance().addDiagramView((DiagramView) dv);
		}
		this.tabbedPane.removeAll();
		if(selectedPackage != null) {
			selectedPackage.closedPane();
		}
		this.selectedPackage = selectedPackage;
		if(selectedPackage != null) {
			selectedPackage.addSubscriber(this);
		}
		ClassyNode project = selectedPackage;
		while(!(project instanceof Project)) {
			project = project.getParent();
		}
		
		if(diagrams.size() == 0) return;
		
		lbProjectName.setText("<html>" + project.getName() + "<br>Autor: " + ((Project) project).getAuthor() + "<html>");
		lbProjectName.setFont(new Font("Calibri", Font.BOLD, 14));
		DiagramView dv;
		for(Diagram diagram : diagrams) {
			dv = new DiagramView(diagram);
			for(int i = 0; i < MainFrame.getInstance().getDiagramViews().size(); i++) {
				if(dv.equals(MainFrame.getInstance().getDiagramViews().get(i))) {
					dv = MainFrame.getInstance().getDiagramViews().get(i);
					break;
				}
			}
			this.tabbedPane.addTab(diagram.getName(), dv);
		}
	}
	
	@Override
	public void update(Object notification) {
		if(!(notification instanceof PackageViewEvent)) {
			return;
		}
		switch((PackageViewEvent) notification) {
			case ADD_DIAGRAM:
				reloadPackage();
				break;
			case REMOVE_ALL:
				removePackageOrProject();
				break;
			case REMOVE_DIAGRAM:
				reloadPackage();
				break;
			case RENAME_PROJECT:
				updateProject();
				break;
			case RENAME_DIAGRAM:
				reloadPackage();
				break;
			case CHANGE_AUTHOR:
				updateProject();
				break;
		}
	}
	
	public void reloadPackage() {
		List<Diagram> diagrams = new ArrayList<>();
		for(ClassyNode diagram : this.getSelectedPackage().getChildren()) {
			if(diagram instanceof Diagram) diagrams.add((Diagram) diagram);
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
		
		while(!(project instanceof Project)) {
			project = project.getParent();
		}
		
		lbProjectName.setText("<html>" + project.getName() + "<br>Autor: " + ((Project) project).getAuthor() + "<html>");
		lbProjectName.setFont(new Font("Calibri", Font.BOLD, 14));
	}
	
	
	public JLabel getLbProjectName() {
		return lbProjectName;
	}
	
	
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	
	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}
	
	public StateManager getStateManager() {
		return stateManager;
	}
	
	public void setStateManager(StateManager stateManager) {
		this.stateManager = stateManager;
	}
	
	public Package getSelectedPackage() {
		return selectedPackage;
	}
	
	public void setSelectedPackage(Package selectedPackage) {
		this.selectedPackage = selectedPackage;
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
	
	//=================================================================
	public void startDrawFieldState() {
		stateManager.setDrawFieldState();
		
	}
	
	public void startDrawMethodState() {
		stateManager.setDrawMethodState();
		
	}
	
	public void startEditContentState() {
		stateManager.setEditContentState();
		
	}
	
	//=================================================================
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
	
	//=================================================================
	public void startZoomToFitState() {
		stateManager.setZoomToFitState();
		
	}
	
	
	public void startDuplicateAction() {
		stateManager.setDuplicateState();
	}
}
