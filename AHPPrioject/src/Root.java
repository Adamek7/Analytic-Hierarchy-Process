import java.util.ArrayList;
import Jama.Matrix;

public class Root {
	private Matrix A;
	private ArrayList<Criteria> elementsList = new ArrayList<>(); //lista dzieci
	private Matrix priorityVector;
	

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
