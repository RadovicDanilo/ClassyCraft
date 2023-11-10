package main.java.raf.dsw.classycraft.app.gui.swing.view;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.controller.ActionManager;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTree;
import main.java.raf.dsw.classycraft.app.model.message.Message;
import main.java.raf.dsw.classycraft.app.model.observer.ISubscriber;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar toolBar;
    private JTabbedPane tabbedPane;
    private JLabel selectedProject;
    private ClassyTree classyTree;
    private MainFrame(){

    }

    private void initialize(){
        actionManager = new ActionManager();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth*6/10, screenHeight*6/10);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        MyMenuBar menu = new MyMenuBar();
        MyToolBar toolBar = new MyToolBar();

        setJMenuBar(menu);
        add(toolBar, BorderLayout.NORTH);


        JTree projectExplorer = classyTree.generateTree((ProjectExplorer) ApplicationFramework.getInstance().getClassyRepository().getRoot());
        JPanel desktop = new JPanel(new BorderLayout());
        this.tabbedPane = new JTabbedPane();
        this.selectedProject = new JLabel();

        JScrollPane scroll=new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll, desktop);
        desktop.add(this.selectedProject, BorderLayout.NORTH);
        desktop.add(tabbedPane, BorderLayout.CENTER);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);
    }

    public static MainFrame getInstance()
    {
        if(instance == null)
        {
            instance = new MainFrame();
            instance.classyTree = new ClassyTreeImplementation();
            instance.initialize();
        }
        return instance;
    }
    public ActionManager getActionManager() {
        return actionManager;
    }


    @Override
    public void update(Object notification) {
        if(!(notification instanceof Message)){
            return;
        }
        JOptionPane messageOptionPane = new JOptionPane();
        messageOptionPane.setMessage(((Message) notification).getText());
        switch (((Message) notification).getMessageType()){
            case INFO: messageOptionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
            break;
            case WARNING: messageOptionPane.setMessageType(JOptionPane.WARNING_MESSAGE);
            break;
            case ERROR: messageOptionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
            break;
        }
        JDialog messageDialog = new JDialog();
        switch (((Message) notification).getSystemEvent()){
            case NAME_CANNOT_BE_EMPTY:messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
                messageDialog = messageOptionPane.createDialog("Naziv nesme biti prazan");
                break;
            case NODE_CANNOT_BE_DELETED:messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
                messageDialog = messageOptionPane.createDialog("Cvor ne moze biti obrisan");
                break;
            case CANNOT_REMOVE_ROOT:messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
                messageDialog = messageOptionPane.createDialog("Project explore ne moze da se ukloni");
                break;
            case CANNOT_ADD_DIAGRAM_TO_ROOT_OR_DIAGRAM:messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
                messageDialog = messageOptionPane.createDialog("Diagram ne moze da se kreira");
                break;
            case CANNOT_ADD_PACKAGE_TO_ROOT_OR_DIAGRAM:messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
                messageDialog = messageOptionPane.createDialog("Paket ne moze da se kreira");
                break;
            default: return;
            case CHANGE_AUTHOR_CAN_ONLY_BE_PREFORMED_ON_PROJECTS:messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
                messageDialog = messageOptionPane.createDialog("Promena autora");
                break;
        }
        messageDialog.show();
    }
    public ClassyTree getClassyTree() {
        return classyTree;
    }

    public void openTabs(List<Diagram> diagrams){
        this.tabbedPane.removeAll();
        ClassyNode project = diagrams.get(0);
        while(!(project instanceof Project)){
            project = project.getParent();
        }
        this.getSelectedProject().setText(project.getName());
        for(Diagram diagram: diagrams){
            this.tabbedPane.addTab(diagram.getName(), new JPanel());
        }
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public JLabel getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(JLabel selectedProject) {
        this.selectedProject = selectedProject;
    }
}
