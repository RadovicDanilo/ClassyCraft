package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content;

import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;

public class Method extends ClassContent {
	private final String returnValue;
	
	public Method(String name, Visibility visibility, String returnValue) {
		super(name, visibility);
		this.returnValue = returnValue;
	}
	
	@Override
	public String toString() {
		return getVisibility().toString() + " " + getName() + "()" + ": " + returnValue;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Method) {
			return ((Method) obj).getName().equals(this.getName()) && ((Method) obj).getReturnValue().equals(this.getReturnValue());
		}
		return false;
	}
	
	public String getReturnValue() {
		return returnValue;
	}
	
}
