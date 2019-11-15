package testCase2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.Random;

public class TestCase2 {
	/*
	 * For two priority queues given in PQ_BUY and PQ_SELL in the cw, with random price and share value
	 * The BUY price 􏰀x is randomly distributed in [80, 110]. The SELL price 􏰀y is randomly distributed in [100, 130]
	 * The amount is a random number drawn from {100, 200, ..., 1000}
	 */
	public static void main(String[] args) throws IOException {
		CreateBuy();
		CreateSell();
	}

	public static void CreateBuy() throws IOException {
		File file =new File("./coreCode/src/def/BuyOrder.csv");
		Writer out =new FileWriter(file);
		int i = 0;
		while(i < 5) {
			double a = (Math.random()*3 + 8)*10;
		    DecimalFormat df = new DecimalFormat("0.00");
		    String str=df.format(a);
		    Random r = new Random();
	        int number = (r.nextInt(10)+1)*100;
			String data1= str + "," + number + "\r\n";
			out.write(data1);
			i++;
		}
		out.close();
	}
	public static void CreateSell() throws IOException {
		File file =new File("./coreCode/src/def/SellOrder.csv");
		Writer out =new FileWriter(file);
		int i = 0;
		while(i < 5) {
			double a = (Math.random()*3 + 10)*10;
		    DecimalFormat df = new DecimalFormat("0.00");
		    String str=df.format(a);
		    Random r = new Random();
	        int number = (r.nextInt(10)+1)*100;
			String data1= str + "," + number + "\r\n";
			out.write(data1);
			i++;
		}
		out.close();
	}
}
