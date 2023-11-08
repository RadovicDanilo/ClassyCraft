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
                message = new Message(systemEvent, MessageType.INFO,"Promene ce se primenit pri ponovno pokretanju aplikacije");
                break;
            case NAME_CANNOT_BE_EMPTY:
                message = new Message(systemEvent, MessageType.ERROR,"Naziv nesme biti prazan");
                break;
            case NODE_CANNOT_BE_DELETED:
                message = new Message(systemEvent, MessageType.ERROR,"Cvor ne moze biti obrisan");
                break;
            case NODE_CANNOT_BE_DUPLICATE:
                message = new Message(systemEvent, MessageType.ERROR,"Ne moze se dodati cvor koji vec postoji");
                break;
            case CANNOT_REMOVE_ROOT:
                message = new Message(systemEvent, MessageType.ERROR,"Project Explorer ne moze da se ukloni");
                break;
            case CANNOT_ADD_DIAGRAM_TO_ROOT_OR_DIAGRAM:
                message = new Message(systemEvent, MessageType.ERROR,"Diagram se mogu praviti samo u projektima ili paketima");
                break;
            case CANNOT_ADD_PACKAGE_TO_ROOT_OR_DIAGRAM:
                message = new Message(systemEvent, MessageType.ERROR,"Paketi se mogu praviti samo u projektima ili drugim paketima");
                break;
            default: return;
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
