package main.java.raf.dsw.classycraft.app.model.message;

import main.java.raf.dsw.classycraft.app.model.observer.IPublisher;
import main.java.raf.dsw.classycraft.app.model.observer.ISubscriber;

import java.util.ArrayList;

public class MessageGenerator implements IPublisher {

    private ArrayList<ISubscriber> subscribers;
    public void GenerateMessage(SystemEvent systemEvent){
        Message message = null;
        switch (systemEvent){
            case THEME_CHANGED:
                message = new Message(SystemEvent.THEME_CHANGED, MessageType.INFO,"Promene ce se primenit pri ponovno pokretanju aplikacije");
                break;
            case NAME_CANNOT_BE_EMPTY:
                message = new Message(SystemEvent.NAME_CANNOT_BE_EMPTY, MessageType.ERROR,"Naziv nesme biti prazan");
                break;
            case NODE_CANNOT_BE_DELETED:
                message = new Message(SystemEvent.NODE_CANNOT_BE_DELETED, MessageType.ERROR,"Cvor ne moze biti obrisan");
                break;
            case NODE_CANNOT_BE_DUPLICATE:
                message = new Message(SystemEvent.NODE_CANNOT_BE_DUPLICATE, MessageType.ERROR,"Ne moze se dodati cvor koji vec postoji");
                break;
        }
        notifySubscribers(message);
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub == null)
            return;
        if(this.subscribers ==null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }
    @Override
    public void removeSubscriber(ISubscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        if(notification instanceof Message){
            for(ISubscriber s: subscribers){
                s.update(notification);
            }
        }
    }
}
