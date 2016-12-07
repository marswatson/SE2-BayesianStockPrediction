package Bayesian;

import java.io.BufferedReader;
import java.io.FileReader;


public class ReadCSV {
	private double[] price;
	private String[] date;
	public void load(String str){
		int count = 0;
		double[] price_data = new double[100];
		String[] date_data = new String[100];
		try {  
			//read from csv
            BufferedReader reader = new BufferedReader(new FileReader(str));
            String line = null;  
            while((line=reader.readLine())!=null){  
            	//csv file split by ,
                String item[] = line.split(",");
                String last = item[1];
                String first = item[0];
                //save the data from csv file
                double value = Double.parseDouble(last);
                price_data[count] = value;
                date_data[count] = first;
                count++;
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		//reverse the data
		int n = count - 1;
		price = new double[count];
		date = new String[count];
		for(int i = n; i >= 0; i--){
			price[n - i] = price_data[i];
			date[n - i] = date_data[i];
		}
	}
	//return price array
	public double[] getprice(){
		return price;
	}
	public String[] getdate(){
		return date;
	}
}



