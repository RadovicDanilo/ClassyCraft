package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Enum extends InterClass {
    ArrayList<String> contents;

    public Enum(ClassyNodeComposite parent, String name, int x, int y, Visibility visibility) {
        super(parent, name, x, y, visibility);
        contents = new ArrayList<>();
        ((Diagram) getParent()).notifySubscribers("");
    }

    public Enum() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Enum) {
            return ((Enum) obj).getName().equals(this.getName());
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
            pw.println();
            pw.println(getVisibility().toCode() + " enum " + getName() + "{");
            pw.println();

            for (String e : contents) {
                pw.println("\t" + e + ",");
            }
            pw.print("}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pw.close();
    }

    public ArrayList<String> getContents() {
        return contents;
    }

    public void setContents(ArrayList<String> contents) {
        this.contents = contents;
        if (getParent() != null)
            ((Diagram) getParent()).notifySubscribers("");

    }

    public void addEnum(String e) {
        if (e == null)
            return;
        if (!contents.contains(e)) {
            contents.add(e);
            ((Diagram) getParent()).notifySubscribers("");
        }

    }
}
