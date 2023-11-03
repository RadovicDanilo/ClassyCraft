package main.java.raf.dsw.classycraft.app.model.logger;

public class ConsoleLogger extends Logger{
    @Override
    public void update(Object notification) {
        System.out.println(notification.toString());
    }
}
