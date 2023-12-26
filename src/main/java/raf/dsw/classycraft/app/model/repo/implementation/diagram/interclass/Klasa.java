package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNode;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Connection;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.ConnectionWithField;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Generalisation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.ClassContent;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Field;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

public class Klasa extends InterClass {
    private ArrayList<ClassContent> contents;

    public Klasa(ClassyNodeComposite parent, String name, int x, int y, Visibility visibility) {
        super(parent, name, x, y, visibility);
        contents = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Klasa) {
            return ((Klasa) obj).getName().equals(this.getName());
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
            Klasa superCLass = null;
            ArrayList<Interface> interfaces = new ArrayList<>();
            for (ClassyNode classyNode : ((ClassyNodeComposite) this.getParent()).getChildren()) {
                if (classyNode instanceof Generalisation && ((Connection) classyNode).getFrom() == this) {
                    if (((Generalisation) classyNode).getTo() instanceof Interface) {
                        interfaces.add((Interface) ((Generalisation) classyNode).getTo());
                    }
                    if (((Generalisation) classyNode).getTo() instanceof Klasa) {
                        superCLass = (Klasa) ((Generalisation) classyNode).getTo();
                    }
                }

            }
            String top = getVisibility().toCode() + " class " + getName();
            if (superCLass != null) {
                top += " extends " + superCLass.getName();
            }
            if (interfaces.size() != 0) {
                top += " implements ";
            }
            for (Interface i : interfaces) {
                top += i.getName() + ",";
            }
            top = top.substring(0, top.length() - 1) + "{";
            pw.println(top);

            for (ClassContent content : contents) {
                if (content instanceof Method) {
                    break;
                }
                pw.println(content.toCode());
            }

            for (ClassyNode classyNode : ((ClassyNodeComposite) this.getParent()).getChildren()) {
                if ((classyNode instanceof ConnectionWithField) && ((Connection) classyNode).getFrom() == this) {
                    pw.println(((ConnectionWithField) classyNode).toCode());
                }
            }

            if (superCLass != null) {
                for (ClassContent c : superCLass.getContents()) {
                    pw.println(c.toCode());
                }
            }

            for (ClassContent content : contents) {
                if (content instanceof Field) {
                    continue;
                }
                pw.println(content.toCode());
            }

            for (Interface i : interfaces) {
                for (Method m : i.getMethods()) {
                    pw.println(m.toCode());
                }
            }
            pw.println("}");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pw.close();
    }

    public ArrayList<ClassContent> getContents() {
        return contents;
    }

    public void setContents(ArrayList<ClassContent> contents) {
        this.contents = contents;
        ((Diagram) getParent()).notifySubscribers("");

    }

    public void addMethod(Method m) {
        if (m == null)
            return;
        if (!contents.contains(m)) {
            contents.add(m);
        }

        Comparator<? super ClassContent> comparator = (Comparator<ClassContent>) (o1, o2) -> {
            if (o1 instanceof Method)
                return 1;
            return -1;
        };
        contents.sort(comparator);

        ((Diagram) getParent()).notifySubscribers("");

    }

    public void addField(Field f) {
        if (f == null)
            return;
        if (!(contents.contains(f))) {
            contents.add(f);
        }

        Comparator<? super ClassContent> comparator = (Comparator<ClassContent>) (o1, o2) -> {
            if (o1 instanceof Method && o2 instanceof Field)
                return 1;
            if (o1 instanceof Field && o2 instanceof Method)
                return -1;
            return 0;

        };
        contents.sort(comparator);

        ((Diagram) getParent()).notifySubscribers("");
    }

    public void removeField(Field field) {
        contents.remove(field);
        ((Diagram) getParent()).notifySubscribers("");
    }

    public void removeMethod(Method method) {
        contents.remove(method);
        ((Diagram) getParent()).notifySubscribers("");
    }
}
