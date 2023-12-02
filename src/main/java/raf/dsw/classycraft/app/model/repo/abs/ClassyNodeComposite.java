package main.java.raf.dsw.classycraft.app.model.repo.abs;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.observer.notifications.PackageViewEvent;
import main.java.raf.dsw.classycraft.app.model.observer.notifications.SystemEvent;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.ProjectExplorer;

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
		if(c instanceof Diagram){
			((Package) this).notifySubscribers(PackageViewEvent.ADD_DIAGRAM);
		}
	}
	
	public void removeChild(ClassyNode c) {
		this.children.remove(c);
		if(c instanceof  ClassyNodeLeaf){
			((Diagram)this).notifySubscribers("");
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
