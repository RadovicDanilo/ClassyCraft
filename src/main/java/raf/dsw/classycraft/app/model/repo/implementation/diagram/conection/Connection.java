package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import main.java.raf.dsw.classycraft.app.model.repo.abs.ClassyNodeComposite;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.DiagramElement;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.InterClass;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;

public abstract class Connection extends DiagramElement {
    private static int field = 0;
    private String toName;
    private String fromName;
    @JsonIgnore
    private InterClass from;
    @JsonIgnore

    private InterClass to;


    public Connection(ClassyNodeComposite parent, String name, InterClass from, InterClass to) {
        super(parent, name);
        this.from = from;
        this.to = to;
        fromName = from.getName();
        toName = to.getName();
    }

    public Connection() {
    }

    public static int getField() {
        return field;
    }

    public static void setField(int field) {
        Connection.field = field;
    }

    @Override
    public String getName() {
        String a = "";
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
        return a;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Connection)) {
            return false;
        }
        if(((Connection) obj).getFrom() == null || ((Connection) obj).getTo() == null || this.getFrom() == null || this.getTo() == null){
            return false;
        }
        return (((Connection) obj).from.equals(this.from) && ((Connection) obj).to.equals(this.to))
                || (((Connection) obj).from.equals(this.to) && ((Connection) obj).to.equals(this.from));
    }

    public InterClass getFrom() {
        return from;
    }

    public InterClass getTo() {
        return to;
    }

    public String getToName() {
        if(to!=null){
            toName = to.getName();
        }
        return toName;
    }

    public String getFromName() {
        if(from!=null){
            fromName = from.getName();
        }
        return fromName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public void setFrom(InterClass from) {
        this.from = from;
    }

    public void setTo(InterClass to) {
        this.to = to;
    }

}
