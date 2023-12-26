package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Interface extends InterClass {
    private ArrayList<Method> methods;

    public Interface(ClassyNodeComposite parent, String name, int x, int y, Visibility visibility) {
        super(parent, name, x, y, visibility);
        this.methods = new ArrayList<>();
    }

    public ArrayList<Method> getMethods() {
        return methods;
    }

    public void setMethods(ArrayList<Method> methods) {
        this.methods = methods;
        ((Diagram) getParent()).notifySubscribers("");

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Interface) {
            return ((Interface) obj).getName().equals(this.getName());
        }
        return false;
    }

    @Override
    public void exportAsCode(String path) {
        String fileName = getName() + ".txt";
        File dir = new File(path);
        File actualFile = new File(dir, fileName);
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter(actualFile, true));
            pw.println(getVisibility().toCode() + " interface " + getName() + "{");
            for (Method m : methods) {
                pw.println(m.toCode());
            }
            pw.println("}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pw.close();
    }

    public void addMethod(Method m) {
        if (m == null)
            return;
        if (!methods.contains(m)) {
            methods.add(m);
        }
        ((Diagram) getParent()).notifySubscribers("");
    }

    public void removeMethod(Method method) {
        methods.remove(method);
        ((Diagram) getParent()).notifySubscribers("");

    }
}
