package core;

public class Execute {

	public static void main(String[] args) {
		// set path of the file to read in
		SelectFile file = new SelectFile();
		OrderList buy = new OrderList(file.buyPath);
		OrderList sell = new OrderList(file.sellPath);
		// print the original buyList and sellList
		Executable.PrintBuy(buy.order);
		Executable.PrintSell(sell.order);
		// sort and print the buyList and sellList
		System.out.println("------------------——After Sorting-----------------—--");
		Executable.SortBuyList(buy.order);
		Executable.PrintBuy(buy.order);
		Executable.SortSellList(sell.order);
		Executable.PrintSell(sell.order);
		System.out.println("-------------------After Execution-------------------");
		// execute two lists
		Executable.ExecuteOrder(buy.order,sell.order);
		Executable.PrintBuy(buy.order);
		Executable.PrintSell(sell.order);
	}
}
