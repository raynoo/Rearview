package edu.cs424.traffic.central;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import com.carinov.commons.aider.sql.DBAider;


import processing.core.PApplet;
import processing.core.PFont;

public class SettingsLoader 
{
	private final static String  FILENAME = "config.properties";
	private static SettingsLoader instance;
	private static Properties properties;

	public static PApplet papp;
	private static PFont helvetica;
	public static int scaleFactor;
	private String dir;

	private SettingsLoader(PApplet papp) 
	{
		this.papp = papp;		
		try 
		{
			dir = new File(".").getCanonicalPath();

			if (dir.substring(dir.length() - 4, dir.length()).equalsIgnoreCase("/lib")   || dir.substring(dir.length() - 4, dir.length()).equalsIgnoreCase("/bin")) 
			{
				dir = dir.substring(0, dir.length() - 4);
			}
		} 
		catch (IOException e)
		{
			System.out.println("SettingsLoader.SettingsLoader()" +e.getMessage());
		}
		loadConfigFile();
		loadPappletSetting();
		loadSQLSettings();

		scaleFactor = getConfigValueAsInt(EnumConfig.SCALEFACTOR);
	}

	private void loadSQLSettings()
	{
		com.carinov.commons.aider.sql.SettingsLoader settings = new com.carinov.commons.aider.sql.SettingsLoader(dir + File.separator + "app.settings");
		DBAider.init(settings);
	}

	private void loadPappletSetting() {
		helvetica = papp.loadFont(dir +"/fonts/Helvetica-120.vlw");

	}

	private void loadConfigFile() {
		properties = new Properties();
		try 
		{
			properties.load(new FileInputStream(dir + "/" + FILENAME));

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
	
	public static PFont getHelvetica()
	{
		return helvetica;
	}

}
