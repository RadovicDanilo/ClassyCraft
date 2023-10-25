package main.java.raf.dsw.classycraft.app.model.message;

import main.java.raf.dsw.classycraft.app.model.observer.IPublisher;
import main.java.raf.dsw.classycraft.app.model.observer.ISubscriber;

import java.util.ArrayList;

public class MessageGenerator implements IPublisher {

    private ArrayList<ISubscriber> subscribers;

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
        if(notification instanceof SystemEvent){
            if(notification==SystemEvent.THEME_CHANGED){
                for(ISubscriber s : subscribers){
                    s.update(new Message(MessageType.INFO,"Promene ce se primeniti pri ponovnom pokretanju aplikacije."));
                }
            }
            if(notification==SystemEvent.NODE_CANNOT_BE_DELETED){

            }
            if(notification==SystemEvent.NAME_CANNOT_BE_EMPTY){

            }
        }
    }
}
