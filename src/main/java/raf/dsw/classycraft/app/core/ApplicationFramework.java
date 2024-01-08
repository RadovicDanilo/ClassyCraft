package main.java.raf.dsw.classycraft.app.core;


import main.java.raf.dsw.classycraft.app.gui.swing.view.frame.MainFrame;
import main.java.raf.dsw.classycraft.app.model.logger.LoggerFactory;
import main.java.raf.dsw.classycraft.app.model.logger.LoggerType;
import main.java.raf.dsw.classycraft.app.model.message.MessageGenerator;
import main.java.raf.dsw.classycraft.app.model.repo.ClassyRepository;
import main.java.raf.dsw.classycraft.app.model.repo.ClassyRepositoryImplementation;

import java.io.File;


public class ApplicationFramework {
    private static ApplicationFramework instance;
    public final String TEMPLATES_PATH = System.getProperty("user.home") + "\\Documents\\Diagram templates\\";
    public final String SCREENSHOTS_PATH = System.getProperty("user.home") + "\\Documents\\Diagram screenshots\\";
    public final String LOG_PATH = "src/main/resources/log.txt";
    private MessageGenerator messageGenerator;
    private ClassyRepositoryImplementation classyRepository;

    private ApplicationFramework() {

    }

    public static ApplicationFramework getInstance() {
        if (instance == null) {
            instance = new ApplicationFramework();
            instance.messageGenerator = new MessageGenerator();
            instance.classyRepository = new ClassyRepositoryImplementation();
            instance.getMessageGenerator().addSubscriber(MainFrame.getInstance());
            LoggerFactory loggerFactory = new LoggerFactory();
            instance.getMessageGenerator().addSubscriber(loggerFactory.createLogger(LoggerType.CONSOLE_LOGGER));
            instance.getMessageGenerator().addSubscriber(loggerFactory.createLogger(LoggerType.FILE_LOGGER));
        }
        return instance;
    }

    public void initialize() {
        new File(TEMPLATES_PATH).mkdirs();
        new File(SCREENSHOTS_PATH).mkdirs();
        MainFrame.getInstance().setVisible(true);
    }

    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }

    public ClassyRepository getClassyRepository() {
        return classyRepository;
    }


}
