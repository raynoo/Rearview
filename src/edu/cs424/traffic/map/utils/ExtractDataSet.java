package edu.cs424.traffic.map.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.cs424.traffic.map.dataset.DataPoint;

public class ExtractDataSet {

	public ExtractDataSet() {
		
	}
	
	public static void main(String[] args) {
		ArrayList<DataPoint> locationData = new ArrayList<DataPoint>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("crashAge.xls")));
			
			String line;
			line = br.readLine();
			String[] headers = line.split("\t");
			
			
			while((line = br.readLine()) != null) {
				String[] values = line.split("\t");
				if(values.length > 0) {
					DataPoint dp = new DataPoint();
					dp.setLocation(values[0]);
					for(int i=1; i<values.length; i++) {
						dp.addAttributeValue("Age Group", headers[i], Integer.parseInt(values[i]));
					}
					locationData.add(dp);
				}
			}
			
			for(DataPoint dp:locationData) {
				System.out.println(dp);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
