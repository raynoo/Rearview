package edu.cs424.data.helper;

import java.util.ArrayList;
import java.util.HashMap;

public class ButtonData 
{
	public static HashMap<String,ArrayList<String>> buttonValues;
	public static HashMap<String,String> buttonValueDBMapping;

	static 
	{
		buttonValues = getButtonData();
		buttonValueDBMapping = getDBMapping();
	}

	
	
	private static HashMap<String,ArrayList<String>> getButtonData()
	{
		HashMap<String,ArrayList<String>> buttonValues = new HashMap<String, ArrayList<String>>();
		
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
		alcohol.add("0.0-0.1 BAC");
		alcohol.add("0.1-0.2 BAC");
		alcohol.add("0.2-0.3 BAC");
		alcohol.add("0.3-0.4 BAC");
		alcohol.add("0.4-0.5 BAC");
		alcohol.add("> 5.0 BAC");
		alcohol.add("No Data");
		buttonValues.put("Alcohol", alcohol);
		
		return buttonValues;		

	}



	private static HashMap<String, String> getDBMapping() 
	{
		
		HashMap<String, String> dbMap = new HashMap<String, String>();

		dbMap.put( "Years", "Year" );
		dbMap.put( "Months", "iaccmon" );
		dbMap.put( "Days", "dayofweek" );
		dbMap.put( "Time", "sacctime" );
		dbMap.put( "Hit & Run", "ihitrun" );
		dbMap.put( "Holiday", "iholiday" );
		dbMap.put( "Highway", "iholiday" );
		dbMap.put( "No. of Deaths", "numfatal" );
		dbMap.put( "Speed Limit", "spdlim" );
		dbMap.put( "Driver Age", "iage" );
		dbMap.put( "Driver Sex", "isex" );
		dbMap.put( "Previous DWI", "iprevdwi" );
		dbMap.put( "Body Type", "ibody" );
		dbMap.put( "Speeding", "speeding" );
		dbMap.put( "Alcohol", "ialcres" );

		dbMap.put( "2001", "2001" );
		dbMap.put( "2002", "2002" );
		dbMap.put( "2003", "2003" );
		dbMap.put( "2004", "2004" );
		dbMap.put( "2005", "2005" );
		dbMap.put( "2006", "2006" );
		dbMap.put( "2007", "2007" );
		dbMap.put( "2008", "2008" );
		dbMap.put( "2009", "2009" );
		dbMap.put( "2010", "2010" );

		dbMap.put( "Jan", "1" );
		dbMap.put( "Feb", "2" );
		dbMap.put( "March", "3" );
		dbMap.put( "April", "4" );
		dbMap.put( "May", "5" );
		dbMap.put( "June", "6" );
		dbMap.put( "July", "7" );
		dbMap.put( "Aug", "8" );
		dbMap.put( "Sept", "9" );
		dbMap.put( "Oct", "10" );
		dbMap.put( "Nov", "11" );
		dbMap.put( "Dec", "12" );

		dbMap.put( "Sunday", "1" );
		dbMap.put( "Monday", "2" );
		dbMap.put( "Tuesday", "3" );
		dbMap.put( "Wednesday", "4" );
		dbMap.put( "Thursday", "5" );
		dbMap.put( "Friday", "6" );
		dbMap.put( "Saturday", "7" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc. Data", "13" );

		dbMap.put( "Early Morn", "2" );
		dbMap.put( "Morning", "3" );
		dbMap.put( "Afternoon", "4" );
		dbMap.put( "Evening", "5" );
		dbMap.put( "Night", "6" );
		dbMap.put( "Late Night", "1" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc. Data", "13" );

		dbMap.put( "No Hit-Run", "0" );
		dbMap.put( "Motor Vehicle", "1" );
		dbMap.put( "Pedestrian", "2" );
		dbMap.put( "Parked Vehicle", "3" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc. Data", "13" );

		dbMap.put( "Not a Holiday", "0" );
		dbMap.put( "New Year's Day", "1" );
		dbMap.put( "MLK Day", "2" );
		dbMap.put( "President's Day", "3" );
		dbMap.put( "Memorial Day", "4" );
		dbMap.put( "Independence Day", "5" );
		dbMap.put( "Labor Day", "6" );
		dbMap.put( "Columbus Day", "7" );
		dbMap.put( "Thanksgiving", "8" );
		dbMap.put( "Christmas", "11" );
		dbMap.put( "New Year's Eve", "10" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc Data", "13" );

		dbMap.put( "No Collision", "0" );
		dbMap.put( "Rear-End", "1" );
		dbMap.put( "Head On", "2" );
		dbMap.put( "Angled", "4" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "Sideswipe", "5" );
		dbMap.put( "No Loc Data", "13" );

		dbMap.put( "Not on NH", "0" );
		dbMap.put( "On NH", "1" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc Data", "13" );

		dbMap.put( "Single Death", "1" );
		dbMap.put( "2 Deaths", "2" );
		dbMap.put( "3-5 Deaths", "3" );
		dbMap.put( "5-10 Deaths", "4" );
		dbMap.put( "> 10 Deaths", "5" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc Data", "2001" );

		dbMap.put( "< 16", "1" );
		dbMap.put( "16 - 24", "2" );
		dbMap.put( "25 - 50", "3" );
		dbMap.put( "51 - 75", "4" );
		dbMap.put( "> 75", "5" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc Data", "13" );

		dbMap.put( "Male", "1" );
		dbMap.put( "Female", "2" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc Data", "13" );

		dbMap.put( "0", "0" );
		dbMap.put( "1", "1" );
		dbMap.put( "2", "2" );
		dbMap.put( "> 2", "3" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc Data", "13" );

		dbMap.put( "Car", "1" );
		dbMap.put( "Motorcycle", "2" );
		dbMap.put( "Van", "3" );
		dbMap.put( "Bus", "4" );
		dbMap.put( "Truck", "5" );
		dbMap.put( "Pickup Truck", "6" );
		dbMap.put( "Light Vehicle", "7" );
		dbMap.put( "Utility Vehicle", "8" );
		dbMap.put( "Other", "10" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc Data", "13" );

		dbMap.put( "Yes", "2001" );
		dbMap.put( "No", "2001" );
		dbMap.put( "No Data", "2001" );
		dbMap.put( "No Loc Data", "2001" );

		dbMap.put( "Not Divided", "2001" );
		dbMap.put( "Traffic Barrier", "2001" );
		dbMap.put( "W/O Traffic Barrier", "2001" );
		dbMap.put( "One Way", "2001" );
		dbMap.put( "Ramp", "2001" );
		dbMap.put( "No Data", "2001" );

		dbMap.put( "No BAC", "2001" );
		dbMap.put( "0.0-1.0 BAC", "2001" );
		dbMap.put( "0.1-0.2 BAC", "2001" );
		dbMap.put( "0.2-0.3 BAC", "2001" );
		dbMap.put( "0.3-0.4 BAC", "2001" );
		dbMap.put( "0.4-0.5 BAC", "2001" );
		dbMap.put( "> 0.5 BAC", "2001" );
		dbMap.put( "No Data", "2001" );
		dbMap.put( "No Loc Data", "2001" );
		
		return dbMap;
	}
}
