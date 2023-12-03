package main.java.raf.dsw.classycraft.app.model.repo.implementation.diagram;

public enum Visibility {
	PUBLIC, PRIVATE, PROTECTED, DEFAULT;
	
	@Override
	public String toString() {
		switch(this){
			case PRIVATE:return "-";
			case PUBLIC:return "+";
			case DEFAULT:return "~";
			case PROTECTED:return "#";
		}
		return super.toString();
	}
}
