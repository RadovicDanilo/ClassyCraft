package main.java.raf.dsw.classycraft.app;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		ApplicationFramework main = ApplicationFramework.getInstance();
		main.initialize();
		//TODO UKLONI OVO
		ClassyTreeItem root = MainFrame.getInstance().getClassyTree().getRoot();
		ClassyTreeView view = MainFrame.getInstance().getClassyTree().getTreeView();
		Project a = new Project("AAAAAAAAAAAAA");
		Package b = new Package(a, "AAAAAAAAAAA");
		Diagram c = new Diagram(b, "AAAAAAA");
		MainFrame.getInstance().getClassyTree().addChild(root, a);
		MainFrame.getInstance().getClassyTree().addChild(MainFrame.getInstance().getClassyTree().getNode(a), b);
		MainFrame.getInstance().getClassyTree().addChild(MainFrame.getInstance().getClassyTree().getNode(b), c);
		ArrayList<Diagram> x = new ArrayList<>();
		x.add(c);
		MainFrame.getInstance().getPackageView().openTabs(x,b);
		for (int i = 0; i < view.getRowCount(); i++) {
			view.expandRow(i);
		}
		MainFrame.getInstance().getPackageView().startDrawClassState();
	}
}