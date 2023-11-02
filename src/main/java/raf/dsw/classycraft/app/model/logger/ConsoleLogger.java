package main.java.raf.dsw.classycraft.app.model.logger;

import main.java.raf.dsw.classycraft.app.model.observer.ISubscriber;

public class ConsoleLogger implements ISubscriber {
    private static ConsoleLogger instance;
    private ConsoleLogger(){

    }
    public static ConsoleLogger getInstance(){
        if(instance==null){
            instance = new ConsoleLogger();
        }
        return instance;
    }
    @Override
    public void update(Object notification) {
        System.out.println(notification.toString());
    }
}
