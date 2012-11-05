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
		String e1 = "";
		Findings f1 = new Findings(s11, s12, e1, 0.0f, 0.0f, 0);	
		sending.add(f1);
		
		ArrayList<String> s21 = new ArrayList<String>();
		s21.add("Body Type = Motorcycle");
		s21.add("Driver Age = 51 - 75");
		s21.add("Body Type = > 75");
		String s22 = "Somethings2";
		String e2 = "";
		Findings f2 = new Findings(s21, s22, e2, 0.0f, 0.0f, 0);	
		sending.add(f2);
		
		ArrayList<String> s31 = new ArrayList<String>();
		s31.add("Body Type = Car");
		s31.add("No. of Deaths = 5-10 Deaths");
		s31.add("No. of Deaths = 3-5 Deaths");
		s31.add("No. of Deaths = > 10 Deaths");
		String s32 = "Somethings2";
		String e3 = "";
		Findings f3 = new Findings(s31, s32, e3, 0.0f, 0.0f, 0);	
		sending.add(f3);
		
		ArrayList<String> s41 = new ArrayList<String>();
		s41.add("Body Type = Car");
		s41.add("Alcohol = 0.0-0.1 BAC");
		s41.add("Alcohol = 0.1-0.2 BAC");
		s41.add("Alcohol = 0.2-0.3 BAC");
		s41.add("Alcohol = 0.3-0.4 BAC");
		s41.add("Alcohol = 0.4-0.5 BAC");
		s41.add("Alcohol = > 0.5 BAC");
		s41.add("Time = Early Morn");
		s41.add("Time = Night");
		s41.add("Time = Late Night");
		s41.add("No. of Deaths = > 10 Deaths");
		String s42 = "Somethings2";
		String e4 = "";
		Findings f4 = new Findings(s41, s42, e4, 0.0f, 0.0f, 0);
		sending.add(f4);
		
		ArrayList<String> s51 = new ArrayList<String>();
		s51.add("Body Type = Car");
		s51.add("Alcohol = Not Drunk");
		s51.add("Weather = Fine Weather");
		s51.add("Time = Morning");
		s51.add("Time = Afternoon");
		s51.add("Time = Evening");
		s51.add("Speeding = No");
		String s52 = "Somethings2";
		String e5 = "";
		Findings f5 = new Findings(s51, s52, e5, 0.0f, 0.0f, 0);
		sending.add(f5);
		
		return sending;
	}
	
}
