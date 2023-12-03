package main.java.raf.dsw.classycraft.app.gui.swing.view.frame;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.controller.ActionManager;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import main.java.raf.dsw.classycraft.app.gui.swing.view.bar.MyMenuBar;
import main.java.raf.dsw.classycraft.app.gui.swing.view.bar.MySideBar;
import main.java.raf.dsw.classycraft.app.gui.swing.view.bar.MyToolBar;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.DiagramView;
import main.java.raf.dsw.classycraft.app.gui.swing.view.view.PackageView;
import main.java.raf.dsw.classycraft.app.model.message.Message;
import main.java.raf.dsw.classycraft.app.model.observer.ISubscriber;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ISubscriber {
	private static MainFrame instance;
	private ActionManager actionManager;
	private PackageView packageView;
	private ArrayList<DiagramView> diagramViews;
	private ClassyTreeImplementation classyTree;
	
	private MainFrame() {
	
	}
	
	private void initialize() {
		
		actionManager = new ActionManager();
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize(screenWidth * 55 / 100, screenHeight * 55 / 100);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ClassyCrafT");
		
		MyMenuBar menu = new MyMenuBar();
		MyToolBar toolBar = new MyToolBar();
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
	
	public static MainFrame getInstance() {
		if(instance == null) {
			instance = new MainFrame();
			instance.diagramViews = new ArrayList<>();
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
		if(notification instanceof Message) {
			displayMessage((Message) notification);
		}
	}
	
	public void displayMessage(Message message) {
		JOptionPane messageOptionPane = new JOptionPane();
		messageOptionPane.setMessage(message.getText());
		switch(message.getMessageType()) {
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
		switch(message.getSystemEvent()) {
			case NAME_CANNOT_BE_EMPTY:
				messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
				messageDialog = messageOptionPane.createDialog("Naziv nesme biti prazan");
				break;
			case NODE_CANNOT_BE_DELETED:
				messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
				messageDialog = messageOptionPane.createDialog("Cvor ne moze biti obrisan");
				break;
			case CANNOT_REMOVE_ROOT:
				messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
				messageDialog = messageOptionPane.createDialog("Project explore ne moze da se ukloni");
				break;
			case DIAGRAM_CAN_ONLY_BE_ADDED_TO_PACKAGE:
				messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
				messageDialog = messageOptionPane.createDialog("Diagram ne moze da se kreira");
				break;
			case CANNOT_ADD_PACKAGE_TO_ROOT_OR_DIAGRAM:
				messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
				messageDialog = messageOptionPane.createDialog("Paket ne moze da se kreira");
				break;
			case CHANGE_AUTHOR_CAN_ONLY_BE_PREFORMED_ON_PROJECTS:
				messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
				messageDialog = messageOptionPane.createDialog("Promena autora");
				break;
			case NODE_CANNOT_BE_DUPLICATE:
				messageOptionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
				messageDialog = messageOptionPane.createDialog("Cvor sa ovim imenom vec postoji");
				break;
			default:
				return;
		}
		messageDialog.setVisible(true);
	}
	
	public ClassyTreeImplementation getClassyTree() {
		return classyTree;
	}
	
	public void setClassyTree(ClassyTreeImplementation classyTree) {
		this.classyTree = classyTree;
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
	public void addDiagramView(DiagramView d){
		if(d == null){
			return;
		}
		if(diagramViews.contains(d)){
			return;
		}
		diagramViews.add(d);
	}
}
