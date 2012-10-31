package edu.cs424.data.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ButtonData 
{
	public static HashMap<String,ArrayList<String>> buttonValues;

	static 
	{
		buttonValues = getButtonData();
	}

	private static HashMap<String,ArrayList<String>> getButtonData()
	{
		HashMap<String,ArrayList<String>> buttonValues = new HashMap<String, ArrayList<String>>();

//		for(int i = 0 ; i <  14 ; i++)
//		{
//			buttonValues.put("text " +i, new ArrayList<String>());
//		}
		
		ArrayList<String> years = new ArrayList<String>();
		years.add("2001");
		years.add("2002");
		years.add("2003");
		years.add("2004");
		years.add("2005");
		years.add("2006");
		years.add("2007");
		years.add("2008");
		years.add("2009");
		years.add("2010");
		buttonValues.put("Years", years);
		
		ArrayList<String> months = new ArrayList<String>();
		months.add("Jan");
		months.add("Feb");
		months.add("March");
		months.add("April");
		months.add("May");
		months.add("June");
		months.add("July");
		months.add("Aug");
		months.add("Sepy");
		months.add("Oct");
		months.add("Nov");
		months.add("Dec");			
		buttonValues.put("Months", months);
		
		ArrayList<String> days = new ArrayList<String>();
		days.add("Monday");
		days.add("Tuesday");
		days.add("Wednesday");
		days.add("Thursday");
		days.add("Friday");
		days.add("Saturday");
		days.add("Sunday");
		buttonValues.put("Days", days);
		
		ArrayList<String> time = new ArrayList<String>();
		time.add("Early Morn");
		time.add("Morning");
		time.add("Afternoon");
		time.add("Evening");
		time.add("Night");
		time.add("Late Night");
		time.add("No Data");
		buttonValues.put("Time", time);
		
		ArrayList<String> hitrun = new ArrayList<String>();
		hitrun.add("No Hit-Run");
		hitrun.add("Motor Vehicle");
		hitrun.add("Pedestrian");
		hitrun.add("Parked Vehicle");
		hitrun.add("No Data");
		buttonValues.put("Hit & Run", hitrun);
		
		ArrayList<String> holiday =  new ArrayList<String>();
		holiday.add("Not a Holiday");
		holiday.add("New Year's day");
		holiday.add("MLK Day");
		holiday.add("President's Day");
		holiday.add("Memorial Day");
		holiday.add("Independence Day");
		holiday.add("Labor Day");
		holiday.add("Columbus Day");
		holiday.add("Thanksgiving");
		holiday.add("Christmas");
		holiday.add("New Year's Eve");
		holiday.add("No Data");
		buttonValues.put("Holiday", holiday);
		
//		ArrayList<String> collision =  new ArrayList<String>();
//		collision.add("No Collision");
//		collision.add("Rear-End");
//		collision.add("Head On");
//		collision.add("Angled");
//		collision.add("Sideswipe");
//		collision.add("No Data");
//		buttonValues.put("Collision Type", collision);
		
		ArrayList<String> highway =  new ArrayList<String>();
		highway.add("Not on NH");
		highway.add("On NH");
		highway.add("No Data");
		buttonValues.put("Highway", highway);
		
		ArrayList<String> fatality =  new ArrayList<String>();
		fatality.add("Single Death");
		fatality.add("2 Deaths");
		fatality.add("3-5 Deaths");
		fatality.add("5-10 Deaths");
		fatality.add("> 10 Deaths");
		fatality.add("No Data");
		buttonValues.put("No. of Deaths", fatality);
		
		ArrayList<String> spdlim =  new ArrayList<String>();
		spdlim.add("< 25mph");
		spdlim.add("26 - 50 mph");
		spdlim.add("51 - 75 mph");
		spdlim.add("76 - 100 mph");
		spdlim.add("No Data");
		buttonValues.put("Speed Limit", spdlim);
		
		ArrayList<String> age =  new ArrayList<String>();
		age.add("< 16");
		age.add("16 - 24");
		age.add("25 - 50");
		age.add("51 - 75");
		age.add("> 75");
		age.add("No Data");
		buttonValues.put("Driver Age", age);
		
		ArrayList<String> sex =  new ArrayList<String>();
		sex.add("Male");
		sex.add("Female");
		sex.add("No Data");
		buttonValues.put("Driver Sex", sex);
		
		ArrayList<String> prevdwi =  new ArrayList<String>();
		prevdwi.add("0");
		prevdwi.add("1");
		prevdwi.add("2");
		prevdwi.add("> 2");
		prevdwi.add("No Data");
		buttonValues.put("Previous DWI", prevdwi);

		ArrayList<String> btype =  new ArrayList<String>();
		btype.add("Motorcycle");
		btype.add("Car");
		btype.add("Van");
		btype.add("Bus");
		btype.add("Truck");
		btype.add("Pickup Truck");
		btype.add("Light Vehicle");
		btype.add("Utility Vehicle");
		btype.add("Other");
		btype.add("No Data");
		buttonValues.put("Body Type", btype);
		
		ArrayList<String> speeding =  new ArrayList<String>();
		speeding.add("Yes");
		speeding.add("No");
		speeding.add("No Data");
		buttonValues.put("Speeding", speeding);
		
		ArrayList<String> flow =  new ArrayList<String>();
		flow.add("Not Divided");
		flow.add("Traffic Barrier");
		flow.add("W/O Traffic Barrier");
		flow.add("One Way");
		flow.add("Ramp");
		flow.add("No Data");
		buttonValues.put("Traffic Flow", flow);
		
		ArrayList<String> alcohol =  new ArrayList<String>();
		alcohol.add("No BAC");
		alcohol.add("0.0 - 0.1 BAC");
		alcohol.add("0.1 - 0.2 BAC");
		alcohol.add("0.2 - 0.3 BAC");
		alcohol.add("0.3 - 0.4 BAC");
		alcohol.add("0.4 - 0.5 BAC");
		alcohol.add("> 5.0 BAC");
		alcohol.add("No Data");
		buttonValues.put("Alcohol", alcohol);
		
		return buttonValues;		

	}
}
