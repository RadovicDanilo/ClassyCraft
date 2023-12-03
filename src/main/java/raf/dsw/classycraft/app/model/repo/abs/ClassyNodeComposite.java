package main.java.raf.dsw.classycraft.app.model.repo.abs;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.observer.notifications.PackageViewEvent;
import main.java.raf.dsw.classycraft.app.observer.notifications.SystemEvent;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;

import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNodeComposite extends ClassyNode {
	private List<ClassyNode> children = new ArrayList<>();
	
	public ClassyNodeComposite(ClassyNodeComposite parent, String name) {
		super(parent, name);
	}
	
	public void addChild(ClassyNode c) {
		
		if(this.children == null) {
			this.children = new ArrayList<>();
		}
		if(this.children.contains(c)) {
			ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.NODE_CANNOT_BE_DUPLICATE);
			return;
		}
		
		this.children.add(c);
		
		if(c instanceof Project) {
			MainFrame.getInstance().getClassyTree().addChild(MainFrame.getInstance().getClassyTree().getRoot(), c);
		}else if(c instanceof DiagramElement) {
			ClassyTreeItem parent = MainFrame.getInstance().getClassyTree().getRoot().getNode(this);
			MainFrame.getInstance().getClassyTree().addChild(parent, c);
			
		}else {
			MainFrame.getInstance().getClassyTree().addChild(MainFrame.getInstance().getClassyTree().getSelectedNode(), c);
		}
		
		if(c instanceof Diagram) {
			((Package) this).notifySubscribers(PackageViewEvent.ADD_DIAGRAM);
		}
		if(c instanceof DiagramElement) {
			((Diagram) c.getParent()).notifySubscribers("");
		}
	}
	
	public void removeChild(ClassyNode c) {
		this.children.remove(c);
		if(c instanceof DiagramElement) {
			((Diagram) this).notifySubscribers(c);
		}
		if(c instanceof Diagram) {
			((Package) this).notifySubscribers(PackageViewEvent.REMOVE_DIAGRAM);
		}
		if(c instanceof Package) {
			((Package) c).notifySubscribers(PackageViewEvent.REMOVE_ALL);
			deleteNodeUpdate((ArrayList<ClassyNode>) ((Package) c).getChildren());
		}
		if(c instanceof Project) {
			deleteNodeUpdate((ArrayList<ClassyNode>) ((Project) c).getChildren());
		}
		((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).removeNode(MainFrame.getInstance().getClassyTree().getSelectedNode());
	}
	
	public void deleteNodeUpdate(ArrayList<ClassyNode> children) {
		for(ClassyNode classyNode : children) {
			if(classyNode instanceof Package) {
				((Package) classyNode).notifySubscribers(PackageViewEvent.REMOVE_ALL);
				deleteNodeUpdate((ArrayList<ClassyNode>) ((Package) classyNode).getChildren());
			}
		}
	}
	
	public List<ClassyNode> getChildren() {
		return children;
	}
	
}
