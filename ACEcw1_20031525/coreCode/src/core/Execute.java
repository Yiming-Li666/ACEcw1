package core;

import java.util.ArrayList;

public class Execute {

	public static void main(String[] args) {
		// set path of the file to read in
		SelectFile file = new SelectFile();
		OrderList buy = new OrderList(file.buyPath);
		OrderList sell = new OrderList(file.sellPath);
		ArrayList<String> buyExec = new ArrayList<>();
		ArrayList<String> sellExec = new ArrayList<>();
		// print the original buyList and sellList
		Executable.PrintBuy(buy.order);
		Executable.PrintSell(sell.order);
		// sort and print the buyList and sellList
		System.out.println("------------------——After Sorting-----------------—--");
		Executable.SortBuyList(buy.order);
		Executable.PrintBuy(buy.order);
		Executable.SortSellList(sell.order);
		Executable.PrintSell(sell.order);
		// clone BUY and SELL list to store Executed order
		Executable.CloneList(buy.order,buyExec);
		Executable.CloneList(sell.order,sellExec);
		// execute two lists
		System.out.println("--------------------Execute List---------------------");
		Executable.ExecuteOrder(buy.order,sell.order,buyExec,sellExec);
		Executable.PrintBuy(buyExec);
		Executable.PrintSell(sellExec);
		System.out.println("-------------------After Execution-------------------");
		Executable.PrintBuy(buy.order);
		Executable.PrintSell(sell.order);
	}
}
