package main.java.raf.dsw.classycraft.app.gui.swing.view.frame;

import main.java.raf.dsw.classycraft.app.controller.ActionManager;
import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import main.java.raf.dsw.classycraft.app.gui.swing.view.bar.MyMenuBar;
import main.java.raf.dsw.classycraft.app.gui.swing.view.bar.MySideBar;
import main.java.raf.dsw.classycraft.app.gui.swing.view.bar.MyToolBar;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.PackageView;
import main.java.raf.dsw.classycraft.app.model.message.Message;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;
import main.java.raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;
    private PackageView packageView;
    private ArrayList<DiagramView> diagramViews;
    private ClassyTreeImplementation classyTree;
    private MyToolBar toolBar;

    private MainFrame() {

    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.diagramViews = new ArrayList<>();
            instance.classyTree = new ClassyTreeImplementation();
            instance.initialize();
        }
        return instance;
    }

    public static void setInstance(MainFrame instance) {
        MainFrame.instance = instance;
    }

    private void initialize() {

        actionManager = new ActionManager();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth * 100 / 100, screenHeight * 100 / 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        MyMenuBar menu = new MyMenuBar();
        toolBar = new MyToolBar();
        MySideBar sideBar = new MySideBar();

        setJMenuBar(menu);
        add(toolBar, BorderLayout.NORTH);
        add(sideBar, BorderLayout.EAST);
        JTree projectExplorer = classyTree.generateTree((ProjectExplorer) ApplicationFramework.getInstance().getClassyRepository().getRoot());
        JScrollPane scroll = new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200, 150));

        this.packageView = new PackageView();
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, packageView);
        getContentPane().add(split, BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    @Override
    public void update(Object notification) {
        if (notification instanceof Message) {
            displayMessage((Message) notification);
        }
    }

    public void displayMessage(Message message) {
        JOptionPane messageOptionPane = new JOptionPane();
        messageOptionPane.setMessage(message.getText());
        switch (message.getMessageType()) {
            case INFO:
                messageOptionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                break;
            case WARNING:
                messageOptionPane.setMessageType(JOptionPane.WARNING_MESSAGE);
                break;
            case ERROR:
                messageOptionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                break;
        }
        JDialog messageDialog;
        messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
        messageDialog = messageOptionPane.createDialog(String.valueOf(message.getSystemEvent()));
        messageDialog.setVisible(true);
    }

    public ClassyTreeImplementation getClassyTree() {
        return classyTree;
    }

    public void setClassyTree(ClassyTreeImplementation classyTree) {
        this.classyTree = classyTree;
    }

    public MyToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(MyToolBar toolBar) {
        this.toolBar = toolBar;
    }

    public PackageView getPackageView() {
        return packageView;
    }

    public void setPackageView(PackageView packageView) {
        this.packageView = packageView;
    }

    public ArrayList<DiagramView> getDiagramViews() {
        return diagramViews;
    }

    public void setDiagramViews(ArrayList<DiagramView> diagramViews) {
        this.diagramViews = diagramViews;
    }

    public void addDiagramView(DiagramView d) {
        if (d == null) {
            return;
        }
        if (diagramViews.contains(d)) {
            return;
        }
        diagramViews.add(d);
    }

}
