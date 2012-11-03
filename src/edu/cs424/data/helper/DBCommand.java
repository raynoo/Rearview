package edu.cs424.data.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.modestmaps.geo.Location;

import edu.cs424.traffic.components.BarGraph;
import edu.cs424.traffic.components.BarGraph.Type;
import edu.cs424.traffic.components.MainPanel;
import edu.cs424.traffic.components.MapPanel;
import edu.cs424.traffic.map.dataset.DataPoint;
import edu.cs424.traffic.pubsub.PubSub.Event;
import edu.cs424.traffic.sqliteconn.ConnSqlite;
import edu.cs424.traffic.sqliteconn.FilterData;

public class DBCommand 
{
	static DBCommand instance = null;

	private HashMap<String, ArrayList<DataPoint>> unFilteredData1;
	private HashMap<String, ArrayList<DataPoint>> unFilteredData2;
	
	private HashMap<String, ArrayList<DataPoint>> filteredData1;
	private HashMap<String, ArrayList<DataPoint>> filteredData2;
	
	// storing the filter selection
	// so that when we switch from year/month.day we can use this
	HashMap<String, Set<String>>filterSelection1;
	HashMap<String, Set<String>>filterSelection2;

	BarGraph bar1,bar2;
	MapPanel map;
	MainPanel mainPanel;
	Location topLeft = new Location(47.992f, -115.022f);
	Location bottomRight = new Location(32.401f, -74.592f);

	// singleton for no apparent reasons
	private DBCommand(MainPanel mainPanel)
	{
		this.mainPanel = mainPanel;
		this.map = mainPanel.mapPanel;
		this.bar1 = mainPanel.graph1;
		this.bar2 = mainPanel.graph2;
		
		filteredData1 = new HashMap<String, ArrayList<DataPoint>>();
		filteredData2 = new HashMap<String, ArrayList<DataPoint>>();
		unFilteredData1 = new HashMap<String, ArrayList<DataPoint>>();
		unFilteredData2 = new HashMap<String, ArrayList<DataPoint>>();

	}

	// used when main panel in intiated.. so stupid
	public static DBCommand getInstance(MainPanel mainPanel)
	{
		if( instance == null )
		{
			instance = new DBCommand(mainPanel);
		}		
		return instance;
	}
	// for all the other time
	public static DBCommand getInstance()
	{
		if(instance == null)
		{
			System.out.println("DBCommand.getInstance()" + "This should be called before the other get instance");
			System.exit(1);
		}
		
		return instance;
	}

	public void updateFilter( HashMap<String, Set<String>>filterSelection , Event event)
	{
		// getting the new data
		if(event == Event.CHANGE_FILTER_GRAPH1)
		{
			filterSelection1 = filterSelection;
			unFilteredData1 = ConnSqlite.getCrashData( convertShit( filterSelection ), bar1.currentType);
		}
		else if(event == Event.CHANGE_FILTER_GRAPH2)
		{
			filterSelection2 = filterSelection;
			unFilteredData2 = ConnSqlite.getCrashData( convertShit( filterSelection ), bar2.currentType);
		}
		else
		{
			System.out.println("DBCommand.updateFilter()" + " MAJOR ERROR");
		}
		
		// after getting the data filter data will have only those points that is within the coordinate

		if(event == event.CHANGE_FILTER_GRAPH1)
		{			
			filterData(filteredData1, unFilteredData1);
		}
		else
		{			
			filterData(filteredData2, unFilteredData2);
		}		
		
		mainPanel.forceRedrawAllComponents();
	}

	private void filterData( HashMap<String, ArrayList<DataPoint>> filteredData , HashMap<String, ArrayList<DataPoint>> unFilteredData)
	{	
		filteredData.clear();
		for(String key : unFilteredData.keySet())
		{
			ArrayList<DataPoint> toStore = new ArrayList<DataPoint>();
			ArrayList<DataPoint> temp = unFilteredData.get(key);
			
			for(DataPoint dp : temp)
			{
				if(dp.isInside(topLeft, bottomRight))
				{
					toStore.add(dp);
				}
			}
			
			filteredData.put(key, toStore);
		}		
	}

	public HashMap<String, ArrayList<DataPoint>> getGraphData(Event event)
	{
		if( event == Event.CHANGE_FILTER_GRAPH1 )
		{
			return filteredData1;
		}
		else if( event == Event.CHANGE_FILTER_GRAPH2 )
		{
			return filteredData2;
		}
		else
		{
			System.out.println("DBCommand.getGraphData()" + "exception is here");
			return null;			
		}
	}

	public  void updateVisibleCoordinate(Location topLeft, Location bottomRight)
	{
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
		
		filterData(filteredData1, unFilteredData1);
		filterData(filteredData2, unFilteredData2);
		
		mainPanel.forceRedrawAllComponents();
	}
	
	
	
	public void notifyTimeFilterChange(Event event,Type type,String yearValue , String monthValue)
	{
		if( event == Event.CHANGE_FILTER_GRAPH1 )
		{
			FilterData data = convertShitForDrillDown( filterSelection1 ,type , yearValue , monthValue);
			unFilteredData1 = ConnSqlite.getCrashData(data, type);
			
			filterData(filteredData1, unFilteredData1);
		}
		else if( event == Event.CHANGE_FILTER_GRAPH2 )
		{
			FilterData data = convertShitForDrillDown( filterSelection2 ,type , yearValue , monthValue);
			unFilteredData2 = ConnSqlite.getCrashData(data, type);
			
			filterData(filteredData2, unFilteredData2);
		}
		
		mainPanel.forceRedrawAllComponents();		
	}	
	
	
	
	private FilterData convertShitForDrillDown( HashMap<String, Set<String>> filters , Type type , String yearValue , String monthValue)
	{
		if(type == Type.Year)
		{
			return convertShit(filters);
		}
		else if ( type == Type.Month )
		{
			HashMap<String, Set<String>> duplicate = new HashMap<String, Set<String>>(filters);			
			duplicate.remove("Years");
			Set<String> temp = new HashSet<String>();
			temp.add(yearValue);
			duplicate.put("Years", temp);
			
			return convertShit(duplicate);
		}
		else
		{		
			HashMap<String, Set<String>> duplicate = new HashMap<String, Set<String>>(filters);	
			duplicate.remove("Years");
			Set<String> temp = new HashSet<String>();
			temp.add(yearValue);
			duplicate.put("Years", temp);
			
			duplicate.remove("Months");
			Set<String> temp1 = new HashSet<String>();
			temp1.add(monthValue);
			duplicate.put("Months", temp1);
			
			return convertShit(duplicate);
		}
	}
	
	// from button shit to db shit
	private FilterData convertShit(  HashMap<String, Set<String>> filterData )
	{
		HashMap<String, ArrayList<Integer>> selectedValues = new HashMap<String, ArrayList<Integer>>();
		
		for(String key : filterData.keySet() )
		{
			Set<String> temp = filterData.get(key);
			
			if( temp != null && temp.size() > 0 )
			{
				String columnName = ButtonData.buttonValueDBMapping.get(key);
				ArrayList<Integer> columnValues = new ArrayList<Integer>();
				
				for(String val : temp)
				{
					columnValues.add(new Integer(ButtonData.buttonValueDBMapping.get(val)));
				}
				
				selectedValues.put(columnName, columnValues);
			}
		}
		
		return new FilterData(selectedValues);
	}


}
