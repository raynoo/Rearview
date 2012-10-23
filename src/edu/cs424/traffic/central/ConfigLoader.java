package edu.cs424.traffic.central;

public class ConfigLoader 
{
	private static ConfigLoader instance;
	
	private ConfigLoader() {
		// TODO Auto-generated constructor stub
	}
	
	public static  ConfigLoader getInstance()
	{
		if ( instance == null)
		{
			instance = new ConfigLoader();
		}
		
		return instance;
	}

}
