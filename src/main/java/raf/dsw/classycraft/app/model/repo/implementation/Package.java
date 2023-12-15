package main.java.raf.dsw.classycraft.app.model.repo.implementation;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.observer.IPublisher;
import main.java.raf.dsw.classycraft.app.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

public class Package extends ClassyNodeComposite implements IPublisher {
    private List<ISubscriber> subscribers = new ArrayList<>();

    public Package(ClassyNodeComposite parent, String name) {
        super(parent, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Package))
            return false;
        Package node = (Package) obj;
        return super.getParent() == node.getParent() && super.getName().equals(node.getName());
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for (ISubscriber sub : this.subscribers) sub.update(notification);
    }

    public void closedPane() {
        subscribers = new ArrayList<>();
    }


}
