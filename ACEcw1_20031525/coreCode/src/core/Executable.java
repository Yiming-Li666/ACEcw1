package core;

import java.util.ArrayList;

public class Executable {
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
	
	public static void ExecuteOrder(ArrayList<String> buyOrder, ArrayList<String> sellOrder, ArrayList<String> buyExec, ArrayList<String> sellExec) {
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
					String newSellElement = sell[0]+ "," + (Integer.valueOf(sell[1]).intValue() - Integer.valueOf(buy[1]).intValue());
					//System.out.println(newBuyElement);
					sellOrder.set(0, newSellElement);
				}
			}
			else {
				break;
			}
		}
		//PrintBuy(buyExec);
		int buySize = buyOrder.size();
		int buyExecSize = buyExec.size();
		int sellSize = sellOrder.size();
		int sellExecSize = sellExec.size();
		//System.out.println(buySize);
//		System.out.println(buyExec.size());
		
		for(int i = 0; i < buySize; i++) {
			//PrintBuy(buyExec);
			if(i == buySize-1) {
				//System.out.println(i);
				String[] buy = buyOrder.get(0).split(",");
				//System.out.println(buyExecSize-1-i);
				
				String[] exec = buyExec.get(buyExecSize-1-i).split(",");
				//System.out.println(exec[1]);
				if(Integer.valueOf(buy[1]).intValue()!=Integer.valueOf(exec[1]).intValue()) {
					//System.out.println("in");
					buyExec.remove(buyExec.size()-1);
					String newBuyExecElement = buy[0]+ "," + (Integer.valueOf(exec[1]).intValue() - Integer.valueOf(buy[1]).intValue());
					//System.out.println(newBuyExecElement);
					buyExec.add(newBuyExecElement);
					//PrintBuy(buyExec);
				}
				else {
//					System.out.println(buyExec.size());
//					System.out.println(i);
//					System.out.println(buyExecSize-1-i);
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
		
		for(int i = 0; i < sellSize; i++) {
			if(i == sellSize-1) {
				String[] sell = sellOrder.get(0).split(",");
//				System.out.println(sellExec.size());
//				System.out.println(i);
//				System.out.println(sellExecSize-1-i);
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
//			System.out.println(sellExec.size());
//			System.out.println(i);
//			System.out.println(sellExecSize-1-i);
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
		
	public static void CloneList(ArrayList<String> buy, ArrayList<String> buyExec){
		for(int i = 0; i < buy.size(); i++) {
			buyExec.add(buy.get(i));
		}
	}
}
