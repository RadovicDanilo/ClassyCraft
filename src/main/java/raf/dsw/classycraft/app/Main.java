package main.java.raf.dsw.classycraft.app;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.model.repo.ClassyRepositoryImplementation;

public class Main {
    public static void main(String[] args) {
        ApplicationFramework main = ApplicationFramework.getInstance();
        main.initialize();
    }
}