package edu.cs424.traffic.sqliteconn;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.modestmaps.geo.Location;

import edu.cs424.traffic.central.SettingsLoader;
import edu.cs424.traffic.components.BarGraph.Type;
import edu.cs424.traffic.map.dataset.DataPoint;

public class ConnSqlite {

	static Connection conn;

	static {
		String sqliteDB = "jdbc:sqlite:" + SettingsLoader.dir + File.separator + "RearView2.sqlite";
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

	public static HashMap<String, ArrayList<DataPoint>> getCrashData( FilterData filterData,Type type) 
	{
		String query;
		if ( type == type.Year )
		{
			query =  appendFilters(filterData, "SELECT UID, latitude, longitude, Year FROM f2");
		}
		else if (type == type.Month )
		{
			query =  appendFilters(filterData, "SELECT UID, latitude, longitude, iaccmon FROM f2");
		}
		else
		{
			query =  appendFilters(filterData, "SELECT UID, latitude, longitude, dayofweek FROM f2");
		}

		return executeFilterQuery(query, type);
	}

	private static String appendFilters(FilterData filterData, String query) {

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


	private static HashMap<String, ArrayList<DataPoint>> executeFilterQuery(String query,Type type) 
	{
		Statement stat;
		ResultSet res;
		HashMap<String, ArrayList<DataPoint>> result = new HashMap<String, ArrayList<DataPoint>>();

		try 
		{
			stat = conn.createStatement();
			res = stat.executeQuery(query);

			while( res.next() )
			{
				String key;
				Location loc = new Location(res.getFloat("latitude"), res.getFloat("longitude"));
				
				if ( type == type.Year )
				{
					key = res.getString("Year");
				}
				else if (type == type.Month )
				{
					key = res.getString("iaccmon");
				}
				else
				{
					key = res.getString("dayofweek");
				}
				
				if( !result.containsKey(key) )
				{
					ArrayList<DataPoint> temp = new ArrayList<DataPoint>();
					result.put(key, temp);
				}
				
				DataPoint dp = new DataPoint(res.getString("UID"),loc, key);
				result.get(key).add(dp);				
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return result;
	}



	public static void cleanup() {
		try {
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
