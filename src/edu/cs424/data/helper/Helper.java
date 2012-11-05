package edu.cs424.data.helper;

import java.util.ArrayList;

public class Helper {

	static ArrayList<Findings> sending;
		
	public static ArrayList<Findings> getFindings()
	{
		sending = new ArrayList<Findings>();
		
		ArrayList<String> s11 = new ArrayList<String>();
		s11.add("Driver Age = < 16");
		String s12 = "Somethings";
		Findings f1 = new Findings(s11, s12, "", 0.0f, 0.0f, 0);	
		sending.add(f1);
		
		ArrayList<String> s21 = new ArrayList<String>();
		s21.add("Body Type = Motorcycle");
		s21.add("Driver Age = 51 - 75");
		s21.add("Body Type = > 75");
		String s22 = "Somethings2";
		Findings f2 = new Findings(s21, s22, "", 0.0f, 0.0f, 0);	
		sending.add(f2);
		
		return sending;
	}
	
}
