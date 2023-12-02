package main.java.raf.dsw.classycraft.app.model.repo.diagram.interclass.content;

import main.java.raf.dsw.classycraft.app.model.repo.diagram.Visibility;

import java.util.ArrayList;

public class Method extends ClassContent {
	private String returnValue;
	private ArrayList<String> args;
	
	public Method(String name, Visibility visibility, String returnValue) {
		super(name, visibility);
		this.returnValue = returnValue;
		this.args = new ArrayList<>();
	}
	
	public String getReturnValue() {
		return returnValue;
	}
	
	public ArrayList<String> getArgs() {
		return args;
	}
}
