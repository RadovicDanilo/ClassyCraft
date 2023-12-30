package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Generalisation;
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

    public Interface() {
        super();
    }

    public ArrayList<Method> getMethods() {
        return methods;
    }

    public void setMethods(ArrayList<Method> methods) {
        this.methods = methods;
        if (getParent() != null) {
            getParent().changed();

            ((Diagram) getParent()).notifySubscribers("");
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Interface) {
            return ((Interface) obj).getName().equals(this.getName());
        }
        return false;
    }

    @Override
    public void exportAsCode(String path, String packPath) {
        String fileName = getName() + ".txt";
        File dir = new File(path);
        File actualFile = new File(dir, fileName);
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter(actualFile, true));
            packPath = packPath.replace("/", ".") + "." + getName();
            packPath = packPath.substring(1);
            pw.println(packPath);

            ArrayList<Interface> interfaces = new ArrayList<>();
            for (ClassyNode classyNode : ((ClassyNodeComposite) this.getParent()).getChildren()) {
                if (classyNode instanceof Generalisation && ((Generalisation) classyNode).getFrom() == this && ((Generalisation) classyNode).getTo() instanceof Interface) {
                    interfaces.add((Interface) ((Generalisation) classyNode).getTo());
                }
            }

            String top = getVisibility().toCode() + " interface " + getName();
            if (interfaces.size() != 0) {
                top += " implements ";
            }
            for (Interface i : interfaces) {
                top += i.getName() + ", ";
            }
            if (interfaces.size() != 0)
                top = top.substring(0, top.length() - 2);
            top += "{";
            pw.println(top);
            pw.println();
            for (Method m : methods) {
                pw.println(m.toCode());
                pw.println();

            }
            for (Interface i : interfaces) {
                for (Method m : i.getMethods()) {
                    pw.println(m.toCode());
                    pw.println();

                }
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
        if(getParent() != null){
            getParent().changed();
        }        ((Diagram) getParent()).notifySubscribers("");
    }

    public void removeMethod(Method method) {
        methods.remove(method);
        if(getParent() != null){
            getParent().changed();
        }
        ((Diagram) getParent()).notifySubscribers("");

    }
}
