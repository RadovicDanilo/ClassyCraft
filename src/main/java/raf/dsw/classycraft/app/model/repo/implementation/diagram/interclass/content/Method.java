package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.interclass.content;

import main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram.Visibility;

import java.util.ArrayList;

public class Method extends ClassContent {
	private String returnValue;
	private ArrayList<Field> args;
	
	public Method(String name, Visibility visibility, String returnValue, ArrayList<Field> args) {
		super(name, visibility);
		this.returnValue = returnValue;
		this.args = args;
	}
	
	public String getReturnValue() {
		return returnValue;
	}
	
	public ArrayList<Field> getArgs() {
		return args;
	}
}
