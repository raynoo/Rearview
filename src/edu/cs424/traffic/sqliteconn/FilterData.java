package edu.cs424.traffic.sqliteconn;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterData {

	HashMap<String, ArrayList<Integer>> selectedValues;
	
	public FilterData() {
		selectedValues = new HashMap<String, ArrayList<Integer>>();
	}

	public HashMap<String, ArrayList<Integer>> getSelectedValues() {
		ArrayList<Integer> values = new ArrayList<Integer>();
		values.add(10);
		values.add(3);
		values.add(4);
		ArrayList<Integer> sexValue = new ArrayList<Integer>();
		sexValue.add(2);
		sexValue.add(1);
		selectedValues.put("iholiday", values);
		selectedValues.put("isex", sexValue);
	
		return selectedValues;
	}

	public void setSelectedValues(HashMap<String, ArrayList<Integer>> selectedValues) {
		this.selectedValues = selectedValues;
	}
}
