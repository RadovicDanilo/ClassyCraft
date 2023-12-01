package main.java.raf.dsw.classycraft.app.model.observer;

public interface IPublisher {
    void addSubscriber(ISubscriber sub);//TODO popravi ovo pls

    void removeSubscriber(ISubscriber sub);

    void notifySubscribers(Object notification);
}
