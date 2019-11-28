package core;

import java.util.ArrayList;

public class Executable {
	/*
	 *  Sort the BUY order list
	 *  The priority queue for BUY orders are max-oriented priority queue
	 *  If the price of two BUY orders are the same, the order first enter has higher priority
	 */
	public static void SortBuyList(ArrayList<String> buyOrder) {
		for(int i=0; i < buyOrder.size(); i++){
			String[] flag = buyOrder.get(i).split(",");
			Double max = Double.valueOf(flag[0]).doubleValue();
			// find the max value in the arrayList, sort by the price value
			int maxj = i;
			for(int j=i; j < buyOrder.size(); j++) {
				String[] pair = buyOrder.get(j).split(",");
				if(max < Double.valueOf(pair[0]).doubleValue()) {
					max = Double.valueOf(pair[0]).doubleValue();
					maxj = j;
				}
			}
			ChangePosition(buyOrder, maxj, i);
			
//			String temp = buyOrder.get(i);
//			buyOrder.set(i, buyOrder.get(maxj));
//			buyOrder.set(maxj, temp);
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
	 *  If the price of two SELL orders are the same, the order first enter has higher priority
	 */
	public static void SortSellList(ArrayList<String> sellOrder) {
		for(int i=0; i < sellOrder.size(); i++){
			String[] flag = sellOrder.get(i).split(",");
			Double min = Double.valueOf(flag[0]).doubleValue();
			// find the min value in the arrayList, sort by the price value
			int minj = i;
			for(int j=i; j < sellOrder.size(); j++) {
				String[] pair = sellOrder.get(j).split(",");
				if(min > Double.valueOf(pair[0]).doubleValue()) {
					min = Double.valueOf(pair[0]).doubleValue();
					minj = j;
					ChangePosition(sellOrder, minj, i);
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
	
	/*
	 * Execute BUY and SELL order
	 * Generate two executed list and two list after execution
	 */
	public static void ExecuteOrder(ArrayList<String> buyOrder, ArrayList<String> sellOrder, ArrayList<String> buyExec, ArrayList<String> sellExec) {
		while(!buyOrder.isEmpty() || !buyOrder.isEmpty()) {
			String[] buy = buyOrder.get(0).split(",");
			String[] sell = sellOrder.get(0).split(",");
			if(Double.valueOf(buy[0]).doubleValue() >= Double.valueOf(sell[0]).doubleValue()) {
				if(Integer.valueOf(buy[1]).intValue() > Integer.valueOf(sell[1]).intValue()) {
					String newBuyElement = buy[0]+ "," + (Integer.valueOf(buy[1]).intValue() - Integer.valueOf(sell[1]).intValue());
					buyOrder.set(0, newBuyElement);
					sellOrder.remove(0);
				}
				else if(Integer.valueOf(buy[1]).intValue() == Integer.valueOf(sell[1]).intValue()) {
					buyOrder.remove(0);
					sellOrder.remove(0);
				}
				else {
					buyOrder.remove(0);
					String newSellElement = sell[0]+ "," + (Integer.valueOf(sell[1]).intValue() - Integer.valueOf(buy[1]).intValue());
					sellOrder.set(0, newSellElement);
				}
			}
			else {
				break;
			}
		}
		int buySize = buyOrder.size();
		int buyExecSize = buyExec.size();
		int sellSize = sellOrder.size();
		int sellExecSize = sellExec.size();
		// generate executed BUY order
		for(int i = 0; i < buySize; i++) {
			if(i == buySize-1) {
				String[] buy = buyOrder.get(0).split(",");
				String[] exec = buyExec.get(buyExecSize-1-i).split(",");
				if(Integer.valueOf(buy[1]).intValue()!=Integer.valueOf(exec[1]).intValue()) {
					buyExec.remove(buyExec.size()-1);
					String newBuyExecElement = buy[0]+ "," + (Integer.valueOf(exec[1]).intValue() - Integer.valueOf(buy[1]).intValue());
					buyExec.add(newBuyExecElement);
				}
				else {
					buyExec.remove(buyExec.size()-1);
				}
			}
			else {
				buyExec.remove(buyExec.size()-1);
			}
		}
		if(!buyExec.isEmpty()) {
			String[] temp = buyExec.get(buyExec.size()-1).split(",");
			System.out.println("￥" + temp[0]+ " for BUY order.");
		}
		else {
			System.out.println("No order is executed!");
		}
		// generate executed SELL order
		for(int i = 0; i < sellSize; i++) {
			if(i == sellSize-1) {
				String[] sell = sellOrder.get(0).split(",");
				String[] exec = sellExec.get(sellExecSize-1-i).split(",");
				if(Integer.valueOf(sell[1]).intValue()!=Integer.valueOf(exec[1]).intValue()) {
					sellExec.remove(sellExecSize-1-i);
					String newSellExecElement = sell[0]+ "," + (Integer.valueOf(exec[1]).intValue() - Integer.valueOf(sell[1]).intValue());
					sellExec.add(newSellExecElement);
				}
				else {
					sellExec.remove(sellExecSize-1-i);
				}
			}
			else {
				sellExec.remove(sellExec.size()-1);
			}		
		}
		if(!sellExec.isEmpty()) {
			String[] tempSell = sellExec.get(sellExec.size()-1).split(",");
			System.out.println("￥" + tempSell[0]+ " for SELL order.");
		}
		else {
			System.out.println("No order is executed!");
		}
	}
	
	
	public static void ChangePosition(ArrayList<String> Order, int j, int cur) {
			String temp = Order.get(cur);
			Order.set(cur, Order.get(j));
			Order.set(j, temp);
			for(int i = cur + 1; i <= j; i++) {
				String next = Order.get(i);
				Order.set(i, temp);
				temp = next;
			}
	}
	
	/*
	 * clone an arrayList, to make it stored in different memory address
	 */
	public static void CloneList(ArrayList<String> buy, ArrayList<String> buyExec){
		for(int i = 0; i < buy.size(); i++) {
			buyExec.add(buy.get(i));
		}
	}
}
