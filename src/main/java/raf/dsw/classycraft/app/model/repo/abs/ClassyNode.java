package main.java.raf.dsw.classycraft.app.model.repo.abs;

import main.java.raf.dsw.classycraft.app.core.ApplicationFramework;
import main.java.raf.dsw.classycraft.app.model.observer.notifications.SystemEvent;

public abstract class ClassyNode {
    private ClassyNode parent;
    private String name;
    public ClassyNode(ClassyNode parent, String name) {
        this.parent = parent;
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }

    public ClassyNode getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        for(ClassyNode c: ((ClassyNodeComposite)this.parent).getChildren()){
            System.out.println(name+" | | "+c.getName());
            if(c.getName().equals(name)){
                System.out.println("IME NIJE PROMENJENO");
                ApplicationFramework.getInstance().getMessageGenerator().GenerateMessage(SystemEvent.NODE_CANNOT_BE_DUPLICATE);
                return;
            }
        }
        System.out.println("IME PROMENJENO");
        this.name = name;
    }
}
