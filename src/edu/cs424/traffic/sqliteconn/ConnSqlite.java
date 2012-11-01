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
		catch ( ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static String getCrashes(FilterData filterData) {
		HashMap<String, ArrayList<Integer>> values = filterData.getSelectedValues();
		
		String query = "select latitude, longitude from f2";
		String append = "";
		boolean first1 = true;
		
		if(! values.isEmpty()) {
			append += " where ";

			for(Entry<String, ArrayList<Integer>> filter : values.entrySet()) {

				boolean first2 = true;
				if( !first1 ){
					append += " and ";
				}
				first1 = false;
				append += filter.getKey();
				append += " in ( ";				
				for(Integer num : filter.getValue()) {				
					if ( !first2 ){
						append += ", ";
					}
					first2 = false;
					append += num;				
				}
				append += ")";
			}
		}
		append += ";";
		query += append;
		System.out.println("This is the query " + query);
		return ( query );
	}
	
	public static ArrayList<DataPoint> executeQuery(String query) {
		Statement stat;
		ResultSet res;
		ArrayList<DataPoint> points = new ArrayList<DataPoint>();
		
		try {
			stat = conn.createStatement();
			res = stat.executeQuery(query);
			
			int i = 0;
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
