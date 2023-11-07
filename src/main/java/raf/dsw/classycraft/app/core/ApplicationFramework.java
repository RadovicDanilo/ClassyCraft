package main.java.raf.dsw.classycraft.app.core;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import main.java.raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import main.java.raf.dsw.classycraft.app.model.logger.LoggerFactory;
import main.java.raf.dsw.classycraft.app.model.logger.LoggerType;
import main.java.raf.dsw.classycraft.app.model.message.MessageGenerator;
import main.java.raf.dsw.classycraft.app.model.repo.ClassyRepositoryImplementation;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class ApplicationFramework {
    public final String PROJECTS_PATH = "/projects";
    public final String SETTINGS_PATH = "src/main/resources/settings/settings.txt";
    public final String LOG_PATH = "src/main/resources/log.txt";
    private static ApplicationFramework instance;
    private boolean isDarkTheme;
    private final MessageGenerator messageGenerator = new MessageGenerator();
    private ClassyRepository classyRepository;

    private ApplicationFramework(){

    }

    public void initialize(){

        MainFrame.getInstance().setVisible(true);
    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
            instance.classyRepository = new ClassyRepositoryImplementation();
            instance.loadThemeSettings();
            instance.getMessageGenerator().addSubscriber(MainFrame.getInstance());
            LoggerFactory loggerFactory = new LoggerFactory();
            instance.getMessageGenerator().addSubscriber(loggerFactory.createLogger(LoggerType.CONSOLE_LOGGER));
            instance.getMessageGenerator().addSubscriber(loggerFactory.createLogger(LoggerType.FILE_LOGGER));
        }
        return instance;
    }

    public void loadThemeSettings() {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(SETTINGS_PATH);
            br = new BufferedReader(fr);
            String[] darkThemeSetting = br.readLine().split("=");
            this.isDarkTheme= Objects.equals(darkThemeSetting[1], "true");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                assert br != null;
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                fr.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(isDarkTheme){
            FlatDarkLaf.setup();
        }else{
            FlatLightLaf.setup();
        }
    }

    public boolean isDarkTheme() {
        return isDarkTheme;
    }
    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }

    public ClassyRepository getClassyRepository() {
        return classyRepository;
    }

    public void setClassyRepository(ClassyRepository classyRepository) {
        this.classyRepository = classyRepository;
    }
}
