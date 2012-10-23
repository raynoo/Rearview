package edu.cs424.traffic.central;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

import processing.core.PApplet;
import processing.core.PFont;

public class SettingsLoader 
{
	private final static String  FILENAME = "config.properties";
	private static SettingsLoader instance;
	private static Properties properties;

	static PApplet papp;
	private PFont helvetica;

	private SettingsLoader(PApplet papp) 
	{
		this.papp = papp;
		
		loadConfigFile();
		loadPappletSetting();
	}


	private void loadPappletSetting() {
		  helvetica = papp.loadFont("fonts/Helvetica-120.vlw");
		
	}


	private void loadConfigFile() {
		properties = new Properties();
		try 
		{
			properties.load(new FileInputStream(FILENAME));

		}

		catch (Exception e) 
		{
			System.out.println(e.toString());
		}

	}


	public static  SettingsLoader getInstance( PApplet papp)
	{
		if ( instance == null)
		{
			instance = new SettingsLoader(papp);
		}

		return instance;
	}

	public static String getConfigValueAsString(EnumConfig config)
	{
		return properties.getProperty(config.getPropName());
	}
	public static int getConfigValueAsInt(EnumConfig config)
	{
		return Integer.valueOf(properties.getProperty(config.getPropName()));
	}
	public static boolean getConfigValueAsBoolean(EnumConfig config)
	{
		return Boolean.valueOf(properties.getProperty(config.getPropName()));
	}

}
