package edu.cs424.traffic.sqliteconn;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterData {

	HashMap<String, ArrayList<String>> selectedValues;
	
	public FilterData(HashMap<String, ArrayList<String>> selectedValues) 
	{
		this.selectedValues = selectedValues;
	}

	public HashMap<String, ArrayList<String>> getSelectedValues() {
		return selectedValues;
	}

}
