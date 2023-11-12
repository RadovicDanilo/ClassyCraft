package main.java.raf.dsw.classycraft.app.gui.swing.view;

import main.java.raf.dsw.classycraft.app.model.observer.ISubscriber;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class PackageView extends JPanel implements ISubscriber {
    private JLabel projectName;
    private JTabbedPane tabbedPane;
    private Package selectedPackage;

    public JTabbedPane getTabbedPane() {return tabbedPane;}

    public void setTabbedPane(JTabbedPane tabbedPane) {this.tabbedPane = tabbedPane;}

    public JLabel getProjectName() {return projectName;}

    public void setProjectName(JLabel projectName) {this.projectName = projectName;}

    public Package getSelectedPackage() {return selectedPackage;}

    public void setSelectedPackage(Package selectedPackage) {this.selectedPackage = selectedPackage;}

    public PackageView() {
        super(new BorderLayout());
        this.tabbedPane = new JTabbedPane();
        this.projectName = new JLabel();
        this.add(this.projectName, BorderLayout.NORTH);
        this.add(this.tabbedPane, BorderLayout.CENTER);
    }
    public void openTabs(List<Diagram> diagrams, Package selectedPackage){
        this.tabbedPane.removeAll();

        if(this.getSelectedPackage()!= null){
            this.getSelectedPackage().removeSubscriber(this);
        }
        this.setSelectedPackage(selectedPackage);
        this.getSelectedPackage().addSubscriber(this);

        ClassyNode project = selectedPackage;
        while(!(project instanceof Project)){
            project = project.getParent();
        }

        if(diagrams.size() == 0){

            return;
        }
        this.getProjectName().setText(project.getName());

        DiagramView dv;
        for(Diagram diagram: diagrams){
            dv = new DiagramView(diagram);
            diagram.addSubscriber(dv);
            this.tabbedPane.addTab(diagram.getName(), dv);
        }
    }

    @Override
    public void update(Object notification) {
        List<Diagram> diagrams = new ArrayList<>();
        for(ClassyNode diagram: this.getSelectedPackage().getChildren()){
            if(diagram instanceof Diagram) diagrams.add((Diagram) diagram);
        }
        this.openTabs(diagrams, this.getSelectedPackage());
    }
}
