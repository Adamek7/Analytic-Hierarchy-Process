import Jama.Matrix;

public class Main {
	public static void main(String[] args){
		System.out.println("Witaj w programie wybiaraj¹cym najlepsz¹ z alternatyw na podstawie zadanych kryteriów przy pomocy AHP");
		Root r = new Root();
		Loader.loadFile(r, args[0]);
		Counter.countPriority(r);
		double[][] test = r.getPriorityVector().getArray();
		double max = test[0][0];
		int maxIndex = 1;
		for(int i = 1; i < test.length; i++){
			if(max < test[i][0]){
				max = test[1][0];
				maxIndex = i+1;
			}
		}
		System.out.println("\n\nObliczenia na podstawie pliku: " + args[0]);
		System.out.println("Ostateczny wektor priorytetów:");
		r.getPriorityVector().print(3, 3);
		System.out.println("\nNajlepsza jest opcja: " + maxIndex);
	}
}
