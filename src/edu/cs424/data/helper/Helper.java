package edu.cs424.data.helper;

import java.util.ArrayList;

public class Helper {

	static ArrayList<Findings> sending;
		
	public static ArrayList<Findings> getFindings()
	{
		sending = new ArrayList<Findings>();
		
		ArrayList<String> s11 = new ArrayList<String>();
		s11.add("Driver Age = < 16");
		String s12 = "Traffic accidents are the major cause of death for people" +
				" under the age of 25. From the year 2000 - 2010 the number of people" +
				" in this age group who die because of traffic accidents has reduced steadily.";
		String e1 = "Over the years ( from 2000 - 2004 ), many States passed the so called Graduating " +
				"Drivers Licensing Laws which mandate young drivers to have an adult while driving." +
				"The implementation of such strict laws has certainly helped bring down the number of " +
				"teenage fatalities over the years.";
		Findings f1 = new Findings(s11, s12, e1, 0.0f, 0.0f, 0);	
		sending.add(f1);
		
		ArrayList<String> s21 = new ArrayList<String>();
		s21.add("Body Type = Motorcycle");
		s21.add("Driver Age = 51 - 75");
		s21.add("Driver Age = > 75");
		String s22 = "There is an overall decline in the number of fatalities on the road due to strict regulations all around." +
				" However this is not reflected in the motorcycle fatalities which have actually increased from 2000 to 2010." +
				" This graph shows the especially worrying increase in motorcyclists above the age of 50";
		String e2 = "The major reason for the increase motorcycle fatalities is the increased number of bikers. " +
				"Two events that are as important are the lack of universal biking safety laws and the increase in gas prices";
		Findings f2 = new Findings(s21, s22, e2, 0.0f, 0.0f, 0);	
		sending.add(f2);
		
		ArrayList<String> s31 = new ArrayList<String>();
		s31.add("Body Type = Car");
		s31.add("No. of Deaths = 5-10 Deaths");
		s31.add("No. of Deaths = 3-5 Deaths");
		s31.add("No. of Deaths = > 10 Deaths");
		String s32 = "The number of really large accidents ( ie accidents with more than 5 fatalities ) has decreased over the years." +
				"This is especially true with regards to cars.";
		String e3 = "In 2002 Wisconsin had it biggest road accident in which 10 people were killed. The accident which involved more than 50 vehicles" +
				" led to a widespread adoption of passenger airbags and SRS systems in cars. This has certainly saved many lives.";
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
		String s42 = "If one were to be rash - drinking and driving and driving during the night and late into the night what would be the consequences?" +
				" Records show that close to three thousand people die every year due to such risky driving practices.";
		String e4 = "Unfortunately there seems to be no reduction in this particular trend";
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
		String s52 = "If one were to be cautious - 1) Drive without drinking in fine weather" +
				" 2) Drive within the speed limits" +
				" 3) Drive during the day" +
				" Then the chances of survival are much better";
		String e5 = "The no. of fatalities are less than one third as compared to those in finding3. Plus they are decreasing over the years.";
		Findings f5 = new Findings(s51, s52, e5, 0.0f, 0.0f, 0);
		sending.add(f5);
		
		return sending;
	}
	
}
