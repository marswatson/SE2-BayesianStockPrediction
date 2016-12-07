package Bayesian;
import java.util.Scanner;
import java.util.Vector;
import java.util.Date;

import Jama.*;

public class Bayesian_curve {
	public static void main(String[] args) { 
		//input 10 csv
		 String[] com_name = {"Agilent","Amazon","Apple","BABA","Cisco","FB","Goog","MSFT","TI","Twitter"};
		 String Post = ".csv";
		 String Pre = "Stock/";
		 double[][] predict_price = new double[10][2];
		 double[] actual_price = new double[10];
		 String[] actual_date = new String[10];
		 double[] absolute_error = new double[10];
		 double[] relative_error = new double[10];
		 MeanVariance mv = new MeanVariance();
		 double average_absolute_error = 0;
		 double average_relative_error = 0;
		 //Read file and get Data
		 System.out.println("CompanyName  PredictPrice  Variance  ActualPrice  AbsoluteError  RelativeError(%)  Date");
		 for(int i = 0; i < 10; i++){
			 String file_name = Pre + com_name[i] + Post;
			 ReadCSV fileread = new ReadCSV();
			 fileread.load(file_name);
			 double[] price;
			 String[] date;
			 //get price
			 price = fileread.getprice();
			 date = fileread.getdate();
			 int predict_days = price.length;
			 //calculate price
			 predict_price[i] = mv.Calculate(price,predict_days,date);
			 actual_price[i] = mv.GetActualPrice();
			 actual_date[i] = mv.GetActualDate();
			 absolute_error[i] = Math.abs(predict_price[i][0] - actual_price[i]);
			 relative_error[i] = 100*absolute_error[i]/actual_price[i];
			 average_absolute_error += absolute_error[i];
			 average_relative_error += relative_error[i];
			 System.out.printf("%-13s",com_name[i]);
			 System.out.printf("%-13.2f %-9.2f %-13.2f",predict_price[i][0],predict_price[i][1],actual_price[i]);
			 System.out.printf("%-14.2f %-18.2f",absolute_error[i],relative_error[i]);
			 System.out.println(actual_date[i]);
			 //System.out.println( + );
		 }
		 System.out.printf("The absolute mean error for this ten dataset is %3.2f, the relative average error is %3.2f%%.\n"
				 ,average_absolute_error/10, average_relative_error/10);
		 
		 //test data set
		 double[] dataset1 = {28.32,28.50,27.91,27.31,28.26,28.55,28.65,29.05,28.64,28.11};
		 double[] dataset2 = {25.67,26.87,28.55,29.32,28.26,28.55,30.18,32.11,29.14,28.11};
		 double[] dataset3 = {125.67,126.87,128.55,132.44,123.55,128.88,130.12,134.5,139.21,137.45};
		 double[] dataset4 = {325.67,331.87,331.55,330.42,333.55,332.88,330.12,334.5,335.21,334.45};
		 double[] dataset5 = {1325.67,1321.87,1331.55,1334.42,1333.15,1328.88,1324.12,1330.35,1335.21,1334.45};
		 double[] predict = new double[2];
		 String[] date = {"1","2","3","4","5","6","7","8","9","10","11"};
		 predict = mv.Calculate(dataset5, 10, date);
		 System.out.printf("The predict price is %3.2f, %3.2f.\n",predict[0], predict[1]);
		 return;
	    } 	
}
	

