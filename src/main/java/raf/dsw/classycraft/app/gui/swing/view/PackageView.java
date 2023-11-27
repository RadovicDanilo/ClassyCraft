package main.java.raf.dsw.classycraft.app.gui.swing.view;

import main.java.raf.dsw.classycraft.app.model.observer.ISubscriber;
import main.java.raf.dsw.classycraft.app.model.observer.notifications.PackageViewEvent;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PackageView extends JPanel implements ISubscriber {
    private JLabel lbProjectName;
    private JTabbedPane tabbedPane;
    private Package selectedPackage;
;
    public PackageView() {
        super(new BorderLayout());
        tabbedPane = new JTabbedPane();
        lbProjectName = new JLabel();
        this.add(lbProjectName, BorderLayout.NORTH);
        this.add(tabbedPane, BorderLayout.CENTER);
    }
    public Package getSelectedPackage() {
        return selectedPackage;
    }

    public void setSelectedPackage(Package selectedPackage) {
        this.selectedPackage = selectedPackage;
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

        if(diagrams.size() == 0)
            return;

        lbProjectName.setText("<html>"+project.getName() + "<br>Autor: " + ((Project) project).getAuthor()+"<html>");
        lbProjectName.setFont(new Font("Calibri",Font.BOLD, 14));
        DiagramView dv;
        for(Diagram diagram: diagrams){
            dv = new DiagramView(diagram);
            diagram.addSubscriber(dv);
            this.tabbedPane.addTab(diagram.getName(), dv);
        }
    }
    @Override
    public void update(Object notification) {
        if(!(notification instanceof PackageViewEvent)){
            return;
        }
        switch ((PackageViewEvent)notification){
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
                renameDiagram();
                break;
            case CHANGE_AUTHOR:
                updateProject();
                break;
        }
    }
    public void reloadPackage(){
        List<Diagram> diagrams = new ArrayList<>();
        for(ClassyNode diagram: this.getSelectedPackage().getChildren()){
            if(diagram instanceof Diagram)
                diagrams.add((Diagram) diagram);
        }
        this.openTabs(diagrams, this.getSelectedPackage());
    }
    public void removePackageOrProject(){
        super.removeAll();
        super.revalidate();
        super.repaint();
        super.setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        lbProjectName = new JLabel();
        this.add(lbProjectName, BorderLayout.NORTH);
        this.add(tabbedPane, BorderLayout.CENTER);
    }
    public void updateProject(){
        ClassyNode project = selectedPackage;

        while(!(project instanceof Project)){
            project = project.getParent();
        }

        lbProjectName.setText("<html>"+project.getName() + "<br>Autor: " + ((Project) project).getAuthor()+"<html>");
        lbProjectName.setFont(new Font("Calibri",Font.BOLD, 14));
    }
    public void addDiagram(){
        int amountOfDiagrams = 0;
        for(ClassyNode classyNode: selectedPackage.getChildren()){
            if(classyNode instanceof  Diagram){
                amountOfDiagrams++;
            }
        }

        if(amountOfDiagrams == tabbedPane.getTabCount()+1){
            reloadPackage();
        }
    }
    public void renameDiagram(){
        reloadPackage();
    }

}
