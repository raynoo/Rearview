package edu.cs424.traffic.sqliteconn;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.modestmaps.geo.Location;

import edu.cs424.traffic.components.MapPanel;
import edu.cs424.traffic.map.dataset.DataPoint;

public class ConnSqlite {

	static Connection conn;
	
	static {
		String sqliteDB = "jdbc:sqlite:../RearView2.sqlite";
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection( sqliteDB );
		}
		catch ( ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getCrashByState(FilterData filterData) {

		return appendFilters(filterData, "SELECT latitude, longitude, istatenum FROM f2");
	}
	
	public static String getCrashByYear(FilterData filterData) {

		return appendFilters(filterData, "SELECT latitude, longitude, Year, iaccmon, dayofweek FROM f2");
	}
	
	static String appendFilters(FilterData filterData, String query) {
		
		HashMap<String, ArrayList<Integer>> values = filterData.getSelectedValues();
		
		StringBuffer append = new StringBuffer("");
		boolean first1 = true;
		
		if(! values.isEmpty()) {
			append.append(" where ");

			for(Entry<String, ArrayList<Integer>> filter : values.entrySet()) {

				boolean first2 = true;
				if( !first1 )
					append.append(" and ");
				
				first1 = false;
				append.append(filter.getKey());
				append.append(" in ( ");				
				
				for(Integer num : filter.getValue()) {				
					if ( !first2 )
						append.append(", ");
					
					first2 = false;
					append.append(num);				
				}
				append.append(")");
			}
		}
		append.append(";");
		query += append.toString();
		
		System.out.println("Query: " + query);
		
		return query;
	}
	
	public static String getLocations(FilterData filterData) {
		
		return appendFilters(filterData, "SELECT latitude, longitude FROM f2");
	}
	
	public static ArrayList<DataPoint> executeQuery(String query) {
		Statement stat;
		ResultSet res;
		ArrayList<DataPoint> points = new ArrayList<DataPoint>();
		
		try {
			stat = conn.createStatement();
			res = stat.executeQuery(query);
			
			while( res.next() ){
				DataPoint dp = new DataPoint(res.getDouble("latitude"), res.getDouble("longitude"), 1, false);
				points.add(dp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return points;
	}
	
	public static void cleanup() {
		try {
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
