package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.InterClass;

public abstract class Connection extends DiagramElement {
    private static int field = 0;
    @JsonIgnore
    private final InterClass from;
    private final String fromName;
    @JsonIgnore
    private final InterClass to;
    private final String toName;

    public Connection(ClassyNodeComposite parent, String name, InterClass from, InterClass to) {
        super(parent, name);
        this.from = from;
        this.to = to;
        fromName = from.getName();;
        toName = to.getName();;
    }

    @Override
    public String getName() {
        String a = "";
        String b = " from " + from.getName() + " to " + to.getName();
        if (this instanceof Aggregation) {
            a = "Aggregation";
        }
        if (this instanceof Composition) {
            a = "Composition";
        }
        if (this instanceof Dependency) {
            a = "Dependency";
        }
        if (this instanceof Generalisation) {
            a = "Generalisation";
        }
        return a + b;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Connection)) {
            return false;
        }
        return (((Connection) obj).from.equals(this.from) && ((Connection) obj).to.equals(this.to)) || (((Connection) obj).from.equals(this.to) && ((Connection) obj).to.equals(this.from));
    }

    public InterClass getFrom() {
        return from;
    }

    public InterClass getTo() {
        return to;
    }

    public static int getField() {
        return field;
    }

    public static void setField(int field) {
        Connection.field = field;
    }
}
