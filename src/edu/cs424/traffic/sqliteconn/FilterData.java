package edu.cs424.traffic.sqliteconn;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterData {

	HashMap<String, ArrayList<Integer>> selectedValues;
	
	public FilterData(HashMap<String, ArrayList<Integer>> selectedValues) 
	{
		this.selectedValues = selectedValues;
	}

	public HashMap<String, ArrayList<Integer>> getSelectedValues() {
		return selectedValues;
	}

}
