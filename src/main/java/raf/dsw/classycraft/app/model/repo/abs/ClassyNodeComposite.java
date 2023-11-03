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

    public ClassyNodeComposite(ClassyNode parent, String ime, NodeType type) {
        super(parent, ime, type);
    }

    public List<ClassyNode> getChildren() {return children;}
    public void setChildren(List<ClassyNode> children) {this.children = children;}

    private boolean addChild(ClassyNode c){
        if(this.children == null) {
            this.children = new ArrayList<>();
        }
        if(this.children.contains(c)){
            ApplicationFramework.getInstance().getMessageGenerator().notifySubscribers(new Message(SystemEvent.NODE_CANNOT_BE_ADDED, MessageType.ERROR, "To ime je zauzeto, izaberi drugo."));
            return false;
        }
        this.children.add(c);
        return true;
    }
    private boolean removeChild(ClassyNode c){
        if(this.children.contains(c)){
            ApplicationFramework.getInstance().getMessageGenerator().notifySubscribers(new Message(SystemEvent.NODE_CANNOT_BE_DELETED, MessageType.ERROR, "Odabrani element se ne nalazi na ocekivanom mestu. Mozda je obrisan, pomeren ili mu je ime promenjeno."));
            return false;
        }
        this.children.remove(c);
        return true;
    }
}
