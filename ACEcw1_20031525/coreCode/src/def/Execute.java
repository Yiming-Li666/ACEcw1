package def;

import java.util.ArrayList;

public class Execute {

	public static void main(String[] args) {
		// set path of the file to read in
		SelectFile file = new SelectFile();
		OrderList buy = new OrderList(file.buyPath);
		OrderList sell = new OrderList(file.sellPath);
		// sort and print the buyList and sellList
		SortBuyList(buy.order);
		PrintBuy(buy.order);
		SortSellList(sell.order);
		PrintSell(sell.order);
		System.out.println("-------------------After Execution-------------------");
		// execute two lists
		ExecuteOrder(buy.order,sell.order);
	}

	/*
	 *  Sort the BUY order list
	 *  The priority queue for BUY orders are max-oriented priority queue
	 *  If the price of two BUY orders are the same, the price with less share has higher priority
	 */
	public static void SortBuyList(ArrayList<String> buyOrder) {
		for(int i=0; i < buyOrder.size(); i++){
			String[] flag = buyOrder.get(i).split(",");
			Double max = Double.valueOf(flag[0]).doubleValue();
			int maxj = i;
			for(int j=i; j < buyOrder.size(); j++) {
				String[] pair = buyOrder.get(j).split(",");
				if(max < Double.valueOf(pair[0]).doubleValue()) {
					max = Double.valueOf(pair[0]).doubleValue();
					maxj = j;
				}
			}
			String temp = buyOrder.get(i);
			buyOrder.set(i, buyOrder.get(maxj));
			buyOrder.set(maxj, temp);
		}
		for(int i=0; i < buyOrder.size(); i++) {
			String[] flag = buyOrder.get(i).split(",");
			for(int j=i; j < buyOrder.size(); j++) {
				String[] pair = buyOrder.get(j).split(",");
				if(Double.valueOf(flag[0]).doubleValue() == Double.valueOf(pair[0]).doubleValue()) {
					if(Double.valueOf(flag[1]) > Double.valueOf(pair[1])) {
						String temp = buyOrder.get(i);
						buyOrder.set(i, buyOrder.get(j));
						buyOrder.set(j, temp);
					}
				}
			}
		}
	 }
	
	/*
	 * Print out the BUY order by the given format
	 */
	public static void PrintBuy(ArrayList<String> buyOrder) {
		int counter = 1;
		System.out.print("PQ_BUY = {");
		for(int i=0; i < buyOrder.size(); i++) {
			String[] commands = buyOrder.get(i).split(",");
			if(counter == 1) {
		    	System.out.print("{￥" + commands[0] + "," + commands[1] + "}");
		    }
		    else{
		    	System.out.print(",{￥" + commands[0] + "," + commands[1] + "}");
		    }
			counter++;
		}
		System.out.println("}");
	}

	/*
	 *  Sort the SELL order list
	 *  The priority queue for SELL orders is min-oriented priority queue
	 *  If the price of two SELL orders are the same, the price with less share has higher priority
	 */
	public static void SortSellList(ArrayList<String> sellOrder) {
		for(int i=0; i < sellOrder.size(); i++){
			String[] flag = sellOrder.get(i).split(",");
			Double min = Double.valueOf(flag[0]).doubleValue();
			int minj = i;
			for(int j=i; j < sellOrder.size(); j++) {
				String[] pair = sellOrder.get(j).split(",");
				if(min > Double.valueOf(pair[0]).doubleValue()) {
					min = Double.valueOf(pair[0]).doubleValue();
					minj = j;
				}
			}
			String temp = sellOrder.get(i);
			sellOrder.set(i, sellOrder.get(minj));
			sellOrder.set(minj, temp);
		}
		for(int i=0; i < sellOrder.size(); i++) {
			String[] flag = sellOrder.get(i).split(",");
			for(int j=i; j < sellOrder.size(); j++) {
				String[] pair = sellOrder.get(j).split(",");
				if(Double.valueOf(flag[0]).doubleValue() == Double.valueOf(pair[0]).doubleValue()) {
					if(Double.valueOf(flag[1]) > Double.valueOf(pair[1])) {
						String temp = sellOrder.get(i);
						sellOrder.set(i, sellOrder.get(j));
						sellOrder.set(j, temp);
					}
				}
			}
		}
	}
	
	/*
	 * Print out the SELL order by the given format
	 */
	public static void PrintSell(ArrayList<String> sellOrder) {
		int counter = 1;
		System.out.print("PQ_SELL = {");
		for(int i=0; i < sellOrder.size(); i++) {
			String[] commands = sellOrder.get(i).split(",");
			if(counter == 1) {
		    	System.out.print("{￥" + commands[0] + "," + commands[1] + "}");
		    }
		    else{
		    	System.out.print(",{￥" + commands[0] + "," + commands[1] + "}");
		    }
			counter++;
		}
		System.out.println("}");
	}
	
	
	public static void ExecuteOrder(ArrayList<String> buyOrder, ArrayList<String> sellOrder) {
		while(!buyOrder.isEmpty() || !buyOrder.isEmpty()) {
			String[] buy = buyOrder.get(0).split(",");
			String[] sell = sellOrder.get(0).split(",");
			if(Double.valueOf(buy[0]).doubleValue() >= Double.valueOf(sell[0]).doubleValue()) {
				if(Integer.valueOf(buy[1]).intValue() > Integer.valueOf(sell[1]).intValue()) {
					String newBuyElement = buy[0]+ "," + (Integer.valueOf(buy[1]).intValue() - Integer.valueOf(sell[1]).intValue());
					buyOrder.set(0, newBuyElement);
					//PrintBuy(buyOrder);
					sellOrder.remove(0);
					//System.out.println(sellOrder.get(0));
				}
				else if(Integer.valueOf(buy[1]).intValue() == Integer.valueOf(sell[1]).intValue()) {
					buyOrder.remove(0);
					sellOrder.remove(0);
				}
				else {
					buyOrder.remove(0);
					String newBuyElement = sell[0]+ "," + (Integer.valueOf(sell[1]).intValue() - Integer.valueOf(buy[1]).intValue());
					//System.out.println(newBuyElement);
					sellOrder.set(0, newBuyElement);
				}
			}
			else {
				break;
			}
		}
		PrintBuy(buyOrder);
		PrintSell(sellOrder);
	}
}
