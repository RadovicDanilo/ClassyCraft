package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass;

import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.ClassContent;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Field;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content.Method;

import java.util.ArrayList;
import java.util.Comparator;

public class Klasa extends InterClass {
    private ArrayList<ClassContent> contents;

    public Klasa(ClassyNodeComposite parent, String name, int x, int y) {
        super(parent, name, x, y);
        contents = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Klasa) {
            return ((Klasa) obj).getName().equals(this.getName());
        }
        return false;
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
