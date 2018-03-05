import java.util.ArrayList;

import Jama.Matrix;

public class Criteria {
	private String name;
	private String parent;
	private Matrix A;
	private ArrayList<Criteria> elementsList = new ArrayList<>(); //lista dzieci
	private Matrix priorityVector;
	
	public Criteria(String name){
		this.name = name;
		//this.parent = parent;
		//this.A = A;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setParent(String parent){
		this.parent = parent;
	}
	public String getParent(){
		return this.parent;
	}
	public void setA(Matrix A){
		this.A = A;
	}
	public Matrix getA(){
		return this.A;
	}
	public void setPriorityVector(Matrix priorityVector){
		this.priorityVector = priorityVector;
	}
	public Matrix getPriorityVector(){
		return this.priorityVector;
	}
	public void addElementToList(Criteria cr){
		this.elementsList.add(cr);
	}
	public ArrayList<Criteria> getElementsList(){
		return this.elementsList;
	}
}
