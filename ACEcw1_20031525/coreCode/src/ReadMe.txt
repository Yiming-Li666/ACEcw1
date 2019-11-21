// How to run the program
The main method is in the Execute class.
The test program is seperated from the main program.
When running the program, you need to enter the file names for BUY order and SELL order and you also need to type the file name correctly.
The test file should be stored in the core folder.

//Read in the file
Each line of the file is an order, in the format of price,share.
Each order will be splited by "," to get the price and share value.
For example:
******************************
81.17,400
108.04,100
91.36,800
108.70,500
101.31,100
******************************

// Test
Test case 1 will generate two test files, "BuyOrder.csv" and "SellOrder.csv". The value is the same as the example in the pdf sheet.
You can run the TestCase1 and TestCase2 seperately and then run the core code.

// Result
The program will read in from two files to get BUY order and SELL order to execute them.
The result will be print out as follow:
******************************
Enter the name of buy order file(XXX.csv): BuyOrder.csv
Enter the name of sell order file(XXX.csv): SellOrder.csv
PQ_BUY = {{￥81.17,400},{￥108.04,100},{￥91.36,800},{￥108.70,500},{￥101.31,100}}
PQ_SELL = {{￥127.59,300},{￥119.94,700},{￥122.30,300},{￥101.08,800},{￥111.70,400}}
------------------——After Sorting-----------------—--
PQ_BUY = {{￥108.70,500},{￥108.04,100},{￥101.31,100},{￥91.36,800},{￥81.17,400}}
PQ_SELL = {{￥101.08,800},{￥111.70,400},{￥119.94,700},{￥122.30,300},{￥127.59,300}}
--------------------Execute List---------------------
￥101.31 for BUY order.
￥101.08 for SELL order.
PQ_BUY = {{￥108.70,500},{￥108.04,100},{￥101.31,100}}
PQ_SELL = {{￥101.08,700}}
-------------------After Execution-------------------
PQ_BUY = {{￥91.36,800},{￥81.17,400}}
PQ_SELL = {{￥101.08,100},{￥111.70,400},{￥119.94,700},{￥122.30,300},{￥127.59,300}}
******************************
