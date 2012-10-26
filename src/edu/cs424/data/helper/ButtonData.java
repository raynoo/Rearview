package edu.cs424.data.helper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ButtonData 
{
	public static HashMap<String,Set<String>> buttonValues;

	static {
		buttonValues = getButtonData();
	}

	private static HashMap<String,Set<String>> getButtonData()
	{
		HashMap<String,Set<String>> buttonValues = new HashMap<String, Set<String>>();

		for(int i = 0 ; i < 14 ; i++)
		{
			buttonValues.put("text " +i, new HashSet<String>());
		}

		Set<String> months = new HashSet<String>();
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

		return buttonValues;		

	}
}
