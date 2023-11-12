package main.java.raf.dsw.classycraft.app.model.repo.implementation;

import main.java.raf.dsw.classycraft.app.model.observer.IPublisher;
import main.java.raf.dsw.classycraft.app.model.observer.ISubscriber;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeLeaf;
import java.util.ArrayList;
import java.util.List;

public class Diagram extends ClassyNodeLeaf implements IPublisher {
    private List<ISubscriber> subscribers = new ArrayList<>();
    public Diagram(ClassyNode parent, String name) {
        super(parent, name);
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Diagram))
            return false;
        ClassyNode node = (ClassyNode) obj;
        return  super.getParent()==node.getParent() && super.getName().equals(node.getName());
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(this.subscribers.contains(sub)) this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for(ISubscriber sub: this.subscribers)
            sub.update(notification);
    }
}
