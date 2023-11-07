package main.java.raf.dsw.classycraft.app.model.repo.abs;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.model.message.Message;
import main.java.raf.dsw.classycraft.app.model.message.MessageType;
import main.java.raf.dsw.classycraft.app.model.message.SystemEvent;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.NodeType;

import java.util.ArrayList;
import java.util.List;

public abstract class ClassyNodeComposite extends ClassyNode{
    private List<ClassyNode> children;

    public ClassyNodeComposite(ClassyNode parent, String name, NodeType type) {
        super(parent, name, type);
    }

    public boolean addChild(ClassyNode c){
        if(this.children == null) {
            this.children = new ArrayList<>();
        }
        if(this.children.contains(c)){
            ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.NODE_CANNOT_BE_DUPLICATE);
            return false;
        }
        this.children.add(c);
        return true;
    }
    public void removeChild(ClassyNode c){
        this.children.remove(c);
    }

    public List<ClassyNode> getChildren() {
        return children;
    }

    public void setChildren(List<ClassyNode> children) {
        this.children = children;
    }
}
