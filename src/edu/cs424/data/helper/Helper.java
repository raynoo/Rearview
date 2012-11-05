package edu.cs424.data.helper;

import java.util.ArrayList;

public class Helper {

	static ArrayList<Findings> sending;
		
	public static ArrayList<Findings> getFindings(){
		
		
		ArrayList<String> s11 = new ArrayList<String>();
		s11.add("Driver Age = 1");
		String s12 = "Somethings";
		Findings f1 = new Findings(s11, s12, "", 0.0f, 0.0f, 0);
		sending.add(f1);
		
		
		return sending;
	}
	
}
