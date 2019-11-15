package core;

import java.util.Scanner;

public class SelectFile {
	public String buyPath;
	public String sellPath;
	
	/*
	 * Read in the file name from the user
	 * Set the path of BUY and SELL file
	 */
	public SelectFile() {
		Scanner input=new Scanner(System.in);
		System.out.print("Enter the name of buy order file(XXX.csv): ");
		this.buyPath = "./coreCode/src/core/" + input.nextLine();
		System.out.print("Enter the name of sell order file(XXX.csv): ");
		String sellPath = "./coreCode/src/core/" + input.nextLine(); 
		this.sellPath = sellPath;
		input.close();
	}
}
