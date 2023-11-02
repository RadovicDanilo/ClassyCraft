package main.java.raf.dsw.classycraft.app.model.logger;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.model.observer.ISubscriber;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger implements ISubscriber {

    private static FileLogger instance;
    private FileLogger(){

    }
    public static FileLogger getInstance(){
        if(instance==null){
            instance = new FileLogger();
        }
        return instance;
    }

    @Override
    public void update(Object notification) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(ApplicationFramework.getInstance().LOG_PATH, true));
            pw.println(notification.toString());
            pw.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
