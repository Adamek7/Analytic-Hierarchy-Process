import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Jama.Matrix;

public class Loader {
	
	private static void addCriteriaChilds(NodeList nList, Criteria name){
		Element tmp = null;
		for (int temp = 0; temp < nList.getLength(); temp++){
			Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               if(name.getName().equals(eElement.getAttribute("parent"))){
            	   tmp = eElement;
            	   Criteria c = new Criteria(eElement.getElementsByTagName("name").item(0).getTextContent());
            	   addCriteriaChilds(nList, c);
            	   name.addElementToList(c);;
               }
               else if(name.getElementsList().size() == 0 && name.getName().equals(eElement.getElementsByTagName("name").item(0).getTextContent())){
            	   tmp = eElement;
               }
            }
		}
		if(name.getElementsList().size() > 0){
			
			String matrix = tmp.getElementsByTagName("rank").item(0).getTextContent();
	      	String[] splitedMatrix = matrix.split(" ");
	        double [][] vals = new double[name.getElementsList().size()][name.getElementsList().size()];
	   	   	int count = 0;
	      	for(int i = 0; i < name.getElementsList().size(); i++){
	    	   for(int j = i; j < name.getElementsList().size(); j++){
	   			   if(i == j) vals[i][j] = 1.;
     			   else{
	      			 vals[i][j] = Double.parseDouble(splitedMatrix[count]);
	      			 vals[j][i] = 1 / Double.parseDouble(splitedMatrix[count]);
	   				 count++;  
	   			   }
      		   }
	      	}
	      	Matrix A = new Matrix(vals);
	      	name.setA(A);
		}
		else{
			String matrix = tmp.getElementsByTagName("alternative").item(0).getTextContent();
	      	String[] splitedMatrix = matrix.split(" ");
	      	int size = splitedMatrix.length;
	      	for(int i = 1; i < size; i++){
	      		size -= i;
	      	}
	      	size++;
	        double [][] vals = new double[size][size];
	   	   	int count = 0;
	      	for(int i = 0; i < size; i++){
	    	   for(int j = i; j < size; j++){
	   			   if(i == j) vals[i][j] = 1.;
     			   else{
	      			 vals[i][j] = Double.parseDouble(splitedMatrix[count]);
	      			 vals[j][i] = 1 / Double.parseDouble(splitedMatrix[count]);
	   				 count++;  
	   			   }
      		   }
	        }
	      	Matrix A = new Matrix(vals);
	      	name.setA(A);
		}
	}
	public static void loadFile(Root root, String fileName){
		 try {	
	         File inputFile = new File(fileName);
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         NodeList nList = doc.getElementsByTagName("element");
	         Element rootM = null;
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               if(eElement.getAttribute("parent").equals("")){
	            	   rootM = eElement;
	            	   Criteria c = new Criteria(eElement.getElementsByTagName("name").item(0).getTextContent());
	            	   addCriteriaChilds(nList, c);
	            	   root.addElementToList(c);;
	               }
	            }
	         }
	       String matrix = rootM.getElementsByTagName("rank").item(0).getTextContent();
      	   String[] splitedMatrix = matrix.split(" ");
      	   double [][] vals = new double[root.getElementsList().size()][root.getElementsList().size()];
      	   int count = 0;
      	   for(int i = 0; i < root.getElementsList().size(); i++){
      		   for(int j = i; j < root.getElementsList().size(); j++){
      			   if(i == j) vals[i][j] = 1.;
      			   else{
      				 vals[i][j] = Double.parseDouble(splitedMatrix[count]);
      				 vals[j][i] = 1 / Double.parseDouble(splitedMatrix[count]);
      				 count++;  
      			   }
      		   }
      	   }
      	 Matrix A = new Matrix(vals);
	      	root.setA(A);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}
}
