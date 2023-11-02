package main.java.raf.dsw.classycraft.app.model.logger;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.model.observer.ISubscriber;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
            BufferedWriter bw = new BufferedWriter(new FileWriter(ApplicationFramework.getInstance().LOG_PATH, true));
            bw.append(notification.toString());
            bw.newLine();
            //FileWriter fw = new FileWriter(ApplicationFramework.getInstance().LOG_PATH,true);
            //PrintWriter pw = new PrintWriter(fw);
            //pw.println(notification.toString());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
