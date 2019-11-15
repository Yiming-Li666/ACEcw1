package def;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OrderList {
	public String Path;
	public ArrayList<String> order;
	
	public OrderList(String Path) {
		this.Path = Path;
		ReadOrder();
	}
	
	public void setOrderList(ArrayList<String> order) {
		this.order = order;
	}
	
	/*
	 * Read input from the file entered by user
	 * Every line from the input file is stored as a string in an arrayList
	 */
	public void ReadOrder() {
		BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(Path));
	        String tempString = null;
	        this.order = new ArrayList<String>();
	        while ((tempString = reader.readLine()) != null) {
	        	order.add(tempString);
	        } 			    
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } 
	}
	
	
}
