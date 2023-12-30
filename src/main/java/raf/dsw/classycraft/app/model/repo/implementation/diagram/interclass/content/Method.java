package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content;

import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;

public class Method extends ClassContent {
    private String returnValue;

    public Method(String name, Visibility visibility, String returnValue) {
        super(name, visibility);
        this.returnValue = returnValue;
    }

    public Method() {
        super();
    }

    @Override
    public String toString() {
        return getVisibility().toString() + " " + getName() + "()" + ": " + returnValue;
    }

    @Override
    public int hashCode() {
        String buffer = this.getReturnValue() + this.getName();
        return buffer.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Method) {
            return ((Method) obj).getName().equals(this.getName()) && ((Method) obj).getReturnValue().equals(this.getReturnValue());
        }
        return false;
    }

    public String toCode() {
        return "\t" + getVisibility().toCode() + " " + getReturnValue() + " " + getName() + "() {\n\t\t\n\t}";
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }
}
