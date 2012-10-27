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

		for(int i = 0 ; i < 14 ; i++)
		{
			buttonValues.put("text " +i, new ArrayList<String>());
		}

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
		days.add("mon");
		days.add("tue");
		days.add("wed");
		days.add("thu");
		days.add("fri");
		days.add("sat");
		days.add("sun");
		buttonValues.put("days", days);

		return buttonValues;		

	}
}
