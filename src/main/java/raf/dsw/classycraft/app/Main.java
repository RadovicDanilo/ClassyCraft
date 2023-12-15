package main.java.raf.dsw.classycraft.app;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;

import java.util.ArrayList;

public class Main {
    private static final boolean IS_TEST_MODE = false;

    public static void main(String[] args) {
        ApplicationFramework main = ApplicationFramework.getInstance();
        main.initialize();
        //NE DIRATI OVO. KORISTI SE ZA LAKSE TESTIRANJE PROGRAMA
        if (IS_TEST_MODE) {
            ClassyTreeItem root = MainFrame.getInstance().getClassyTree().getRoot();
            ClassyTreeView view = MainFrame.getInstance().getClassyTree().getTreeView();
            Project a = new Project("PROJEKAT");
            Package b = new Package(a, "PAKET");
            Diagram c = new Diagram(b, "DIAGRAM");
            MainFrame.getInstance().getClassyTree().addChild(root, a);
            MainFrame.getInstance().getClassyTree().addChild(MainFrame.getInstance().getClassyTree().getNode(a), b);
            MainFrame.getInstance().getClassyTree().addChild(MainFrame.getInstance().getClassyTree().getNode(b), c);
            ArrayList<Diagram> x = new ArrayList<>();
            x.add(c);
            MainFrame.getInstance().getPackageView().openTabs(x, b);
            for (int i = 0; i < view.getRowCount(); i++) {
                view.expandRow(i);
            }
            MainFrame.getInstance().getPackageView().startDrawClassState();
        }
    }
}