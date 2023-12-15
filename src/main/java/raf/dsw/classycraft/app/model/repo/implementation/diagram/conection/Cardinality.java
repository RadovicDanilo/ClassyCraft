package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.conection;

public enum Cardinality {
    ONE, MANY;

    @Override
    public String toString() {
        if (this == ONE)
            return "..1";
        return "..*";
    }
}
