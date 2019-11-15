package testCase1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
 
public class TestCase1 {
	/*
	 * For two priority queues given in PQ_BUY and PQ_SELL in the cw, with fixed price and share value
	 */
	public static void main(String[] args) throws IOException {
		CreateBuy();
		CreateSell();
	}
	
	/*
	 * Create BUY order file in the path of "./coreCode/src/def/" as BuyOrder.csv
	 */
	public static void CreateBuy() throws IOException {
		File file =new File("./coreCode/src/core/BuyOrder.csv");
		Writer out =new FileWriter(file);
		String data1="103,200\r\n";
		out.write(data1);
		String data2="102,100\r\n";
		out.write(data2);
		String data3="102,400\r\n";
		out.write(data3);
		String data4="99,400\r\n";
		out.write(data4);
		String data5="98,300\r\n";
		out.write(data5);
		out.close();
	}
	/*
	 * Create SELL order file in the path of "./coreCode/src/def/" as SellOrder.csv
	 */
	public static void CreateSell() throws IOException {
		File file =new File("./coreCode/src/core/SellOrder.csv");
		Writer out =new FileWriter(file);
		String data1="95,200\r\n";
		out.write(data1);
		String data2="98,100\r\n";
		out.write(data2);
		String data3="98,200\r\n";
		out.write(data3);
		String data4="102,100\r\n";
		out.write(data4);
		String data5="103,200\r\n";
		out.write(data5);
		out.close();
	}
	
}
 