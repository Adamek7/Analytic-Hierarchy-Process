import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class Counter {
	private static int dimension; // wymiar wektora priorytetów(liczba alternatyw)
	private static double normalizationTerm = 0.;
	
	private static void setDim(Root r){
		if(r.getElementsList().size() == 0 ) dimension = r.getA().getColumnDimension();
		else{
			Criteria cr = r.getElementsList().get(0);
			while(cr.getElementsList().size() != 0){
				cr = cr.getElementsList().get(0);
			}
			dimension = cr.getA().getColumnDimension();
		}
	}
	private static void getNT(double[][] mat){ // obliczanie normalizaion term
		normalizationTerm = 0;
		double pow = 1;
		for(int i = 0; i < mat.length; i++){
			for(int j = 0; j < mat.length; j++){
				pow *= mat[i][j];
			}
			double pot = 1/(double)mat.length;
			normalizationTerm += Math.pow(pow, pot);
			pow = 1;
		}
	}
	private static Matrix countPriorityVector(Matrix A){
		double [][] tmp = A.getArray();
		double [][] result = new double[tmp.length][1];
		getNT(tmp);
		for(int i = 0; i < tmp.length; i++){
			result[i][0] = 1;
			for(int j = 0; j < tmp.length; j++){
				result[i][0] *= tmp[i][j];
			}
			result[i][0] = Math.pow(result[i][0], 1/(double)tmp.length);
			result[i][0] /= normalizationTerm;
		}
		Matrix B = new Matrix(result);
		return B;
	}
	
	public static void countPriority(Root root){
		setDim(root);
		Matrix pr = new Matrix(dimension, 1);
		double[][] tmp = countPriorityVector(root.getA()).getArray();
		int licz = 0;
		
		for(Criteria c : root.getElementsList()){
			if(c.getPriorityVector() == null) countPriority(c);
	
			pr.plusEquals(c.getPriorityVector().timesEquals(tmp[licz][0]));
		
			licz++;
		}
		//if(root.getElementsList().size() == 0) root.setPriorityVector(countPriorityVector(root.getA()));
		//else{
			root.setPriorityVector(pr);
		//}
		//root.priorityVector.print(10, 10);
		//root.elementsList.get(0).priorityVector.print(10, 10);
	}
	
	private static void countPriority(Criteria cr){
		Matrix pr = new Matrix(dimension, 1);
		double[][] tmp = countPriorityVector(cr.getA()).getArray();
		int licz = 0;
		for(Criteria c : cr.getElementsList()){
			if(c.getPriorityVector() == null) countPriority(c);
			//System.out.println(pr.getRowDimension());
			pr.plusEquals(c.getPriorityVector().timesEquals(tmp[licz][0]));
			//c.priorityVector.print(1, 0);
			//System.out.println(tmp[licz][0]);
			licz++;
		}
		if(cr.getElementsList().size() == 0) {cr.setPriorityVector(countPriorityVector(cr.getA()));
			//System.out.println(normalizationTerm);
			//cr.A.print(10, 10);
			 //cr.priorityVector.print(10, 10);
		}
		else{
			cr.setPriorityVector(pr);
			//cr.priorityVector.print(10, 10);
		}
	}
}
