package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Diagram;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Package;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.Project;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Aggregation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Composition;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Dependency;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection.Generalisation;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Enum;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Interface;
import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.Klasa;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "content_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Field.class, name = "field"),
        @JsonSubTypes.Type(value = Method.class, name = "method"),

})
public abstract class ClassContent {
    private String name;
    private Visibility visibility;

    public ClassContent(String name, Visibility visibility) {
        this.name = name;
        this.visibility = visibility;
    }

    public ClassContent() {
    }

    public abstract String toCode();
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }
}
