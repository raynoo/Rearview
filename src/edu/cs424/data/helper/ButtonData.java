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
		months.add("Sept");
		months.add("Oct");
		months.add("Nov");
		months.add("Dec");			
		buttonValues.put("Months", months);
		
		ArrayList<String> days = new ArrayList<String>();
		days.add("Mon");
		days.add("Tue");
		days.add("Wed");
		days.add("Thu");
		days.add("Fri");
		days.add("Sat");
		days.add("Sun");
		days.add("No Loc. Data");
		buttonValues.put("Days", days);
		
		ArrayList<String> time = new ArrayList<String>();
		time.add("Early Morn");
		time.add("Morning");
		time.add("Afternoon");
		time.add("Evening");
		time.add("Night");
		time.add("Late Night");
		time.add("No Data");
		time.add("No Loc. Data");
		buttonValues.put("Time", time);
		
		ArrayList<String> weather = new ArrayList<String>();
		weather.add("Fine Weather");
		weather.add("Rain");
		weather.add("Sleet");
		weather.add("Snow");
		weather.add("Fog");
		weather.add("Rain+Fog");
		weather.add("Sleet+Fog");
		weather.add("Other");
		weather.add("Unknown");
		weather.add("No Loc. Data");
		buttonValues.put("Weather", weather);
		
		ArrayList<String> hitrun = new ArrayList<String>();
		hitrun.add("No Hit-Run");
		hitrun.add("Motor Vehicle");
		hitrun.add("Pedestrian");
		hitrun.add("Parked Vehicle");
		hitrun.add("No Data");
		hitrun.add("No Loc. Data");
		buttonValues.put("Hit & Run", hitrun);
		
		ArrayList<String> holiday =  new ArrayList<String>();
		holiday.add("Not a Holiday");
		holiday.add("New Year's Day");
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
		holiday.add("No Loc. Data");
		buttonValues.put("Holiday", holiday);
		
		ArrayList<String> highway =  new ArrayList<String>();
		highway.add("Not on NH");
		highway.add("On NH");
		highway.add("No Data");
		highway.add("No Loc Data");
		buttonValues.put("Highway", highway);
		
		ArrayList<String> fatality =  new ArrayList<String>();
		fatality.add("Single Death");
		fatality.add("2 Deaths");
		fatality.add("3-5 Deaths");
		fatality.add("5-10 Deaths");
		fatality.add("> 10 Deaths");
		fatality.add("No Data");
		fatality.add("No Loc Data");
		buttonValues.put("No. of Deaths", fatality);
		
//		ArrayList<String> spdlim =  new ArrayList<String>();
//		spdlim.add("< 25mph");
//		spdlim.add("26 - 50 mph");
//		spdlim.add("51 - 75 mph");
//		spdlim.add("76 - 100 mph");
//		spdlim.add("No Data");
//		buttonValues.put("Speed Limit", spdlim);
		
//		ArrayList<String> manncol = new ArrayList<String>();
//		manncol.add("No Collision");
//		manncol.add("Rear-End");
//		manncol.add("Head On");
//		manncol.add("Angled");
//		manncol.add("Sideswipe");
//		manncol.add("No Data");
//		manncol.add("No Loc Data");
//		buttonValues.put("Collision", manncol);
		
		ArrayList<String> age =  new ArrayList<String>();
		age.add("< 16");
		age.add("16 - 24");
		age.add("25 - 50");
		age.add("51 - 75");
		age.add("> 75");
		age.add("No Data");
		age.add("No Loc Data");
		buttonValues.put("Driver Age", age);
		
		ArrayList<String> sex =  new ArrayList<String>();
		sex.add("Male");
		sex.add("Female");
		sex.add("No Data");
		sex.add("No Loc Data");
		buttonValues.put("Driver Sex", sex);
		
		ArrayList<String> prevdwi =  new ArrayList<String>();
		prevdwi.add("0");
		prevdwi.add("1");
		prevdwi.add("2");
		prevdwi.add("> 2");
		prevdwi.add("No Data");
		prevdwi.add("No Loc Data");
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
		btype.add("No Loc Data");
		buttonValues.put("Body Type", btype);
		
		ArrayList<String> speeding =  new ArrayList<String>();
		speeding.add("Yes");
		speeding.add("No");
		speeding.add("No Data");
		speeding.add("No Loc Data");
		buttonValues.put("Speeding", speeding);
		
		ArrayList<String> flow =  new ArrayList<String>();
		flow.add("Not Divided");
		flow.add("Traffic Barrier");
		flow.add("W/O Traffic Barrier");
		flow.add("One Way");
		flow.add("Ramp");
		flow.add("No Data");
		flow.add("No Loc Data");
		buttonValues.put("Traffic Flow", flow);
		
		ArrayList<String> alcohol =  new ArrayList<String>();
		alcohol.add("Not Drunk");
		alcohol.add("0.0-0.1 BAC");
		alcohol.add("0.1-0.2 BAC");
		alcohol.add("0.2-0.3 BAC");
		alcohol.add("0.3-0.4 BAC");
		alcohol.add("0.4-0.5 BAC");
		alcohol.add("> 0.5 BAC");
		alcohol.add("No Data");
		alcohol.add("No Loc Data");
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
		dbMap.put( "Weather", "iatmcond" );

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

		dbMap.put( "Jan", "Jan" );
		dbMap.put( "Feb", "Feb" );
		dbMap.put( "March", "March" );
		dbMap.put( "April", "April" );
		dbMap.put( "May", "May" );
		dbMap.put( "June", "June" );
		dbMap.put( "July", "July" );
		dbMap.put( "Aug", "Aug" );
		dbMap.put( "Sept", "Sept" );
		dbMap.put( "Oct", "Oct" );
		dbMap.put( "Nov", "Nov" );
		dbMap.put( "Dec", "Dec" );

		dbMap.put( "Sunday", "Sun" );
		dbMap.put( "Monday", "Mon" );
		dbMap.put( "Tuesday", "Tues" );
		dbMap.put( "Wednesday", "Wed" );
		dbMap.put( "Thursday", "Thur" );
		dbMap.put( "Friday", "Fri" );
		dbMap.put( "Saturday", "Sat" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc. Data", "99" );

		dbMap.put( "Early Morn", "2" );
		dbMap.put( "Morning", "3" );
		dbMap.put( "Afternoon", "4" );
		dbMap.put( "Evening", "5" );
		dbMap.put( "Night", "6" );
		dbMap.put( "Late Night", "1" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc. Data", "99" );
		
		dbMap.put( "Fine Weather", "1" );
		dbMap.put( "Rain", "2" );
		dbMap.put( "Sleet", "3" );
		dbMap.put( "Snow", "4" );
		dbMap.put( "Fog", "5" );
		dbMap.put( "Rain+Fog", "6" );
		dbMap.put( "Sleet+Fog", "7" );
		dbMap.put( "Other", "8" );
		dbMap.put( "Unknown", "9" );
		dbMap.put( "No Loc. Data", "13" );

		dbMap.put( "No Hit-Run", "0" );
		dbMap.put( "Motor Vehicle", "1" );
		dbMap.put( "Pedestrian", "2" );
		dbMap.put( "Parked Vehicle", "3" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc. Data", "99" );

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
		dbMap.put( "No Loc Data", "99" );

//		dbMap.put( "No Collision", "0" );
//		dbMap.put( "Rear-End", "1" );
//		dbMap.put( "Head On", "2" );
//		dbMap.put( "Angled", "4" );
//		dbMap.put( "No Data", "9" );
//		dbMap.put( "Sideswipe", "5" );
//		dbMap.put( "No Loc Data", "13" );

		dbMap.put( "Not on NH", "0" );
		dbMap.put( "On NH", "1" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc Data", "99" );

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
		dbMap.put( "No Loc Data", "99" );

		dbMap.put( "Male", "1" );
		dbMap.put( "Female", "2" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc Data", "99" );

		dbMap.put( "0", "0" );
		dbMap.put( "1", "1" );
		dbMap.put( "2", "2" );
		dbMap.put( "> 2", "3" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc Data", "99" );

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
		dbMap.put( "No Loc Data", "99" );

		dbMap.put( "Yes", "1" );
		dbMap.put( "No", "2" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc Data", "99" );

		dbMap.put( "Not Divided", "1" );
		dbMap.put( "Traffic Barrier", "2" );
		dbMap.put( "W/O Traffic Barrier", "3" );
		dbMap.put( "One Way", "4" );
		dbMap.put( "Ramp", "5" );
		dbMap.put( "No Data", "9" );

		dbMap.put( "Not Drunk", "0" );
		dbMap.put( "0.0-0.1 BAC", "1" );
		dbMap.put( "0.1-0.2 BAC", "2" );
		dbMap.put( "0.2-0.3 BAC", "3" );
		dbMap.put( "0.3-0.4 BAC", "4" );
		dbMap.put( "0.4-0.5 BAC", "5" );
		dbMap.put( "> 0.5 BAC", "6" );
		dbMap.put( "No Data", "9" );
		dbMap.put( "No Loc Data", "99" );
		
		return dbMap;
	}
}
