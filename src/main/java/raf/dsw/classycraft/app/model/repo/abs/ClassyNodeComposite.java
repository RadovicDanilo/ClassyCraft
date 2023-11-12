package main.java.raf.dsw.classycraft.app.model.repo.abs;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.model.message.SystemEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNodeComposite extends ClassyNode{
    private List<ClassyNode> children = new ArrayList<>();

    public ClassyNodeComposite(ClassyNode parent, String name) {
        super(parent, name);
    }
    public void addChild(ClassyNode c){
        if(this.children == null) {
            this.children = new ArrayList<>();
        }
        if(this.children.contains(c)){
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.NODE_CANNOT_BE_DUPLICATE);
            return;
        }
        this.children.add(c);
    }
    public void removeChild(ClassyNode c){
        this.children.remove(c);
    }

    public List<ClassyNode> getChildren() {
        return children;
    }

}
