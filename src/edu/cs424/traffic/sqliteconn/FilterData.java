package edu.cs424.traffic.sqliteconn;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterData {

	HashMap<String, ArrayList<Integer>> selectedValues;
	
	public FilterData() {
		selectedValues = new HashMap<String, ArrayList<Integer>>();
	}

	public HashMap<String, ArrayList<Integer>> getSelectedValues() {
		return selectedValues;
	}

	public void setSelectedValues(HashMap<String, ArrayList<Integer>> selectedValues) {
		this.selectedValues = selectedValues;
	}
}
