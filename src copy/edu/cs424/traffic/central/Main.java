package edu.cs424.traffic.central;

import static edu.cs424.data.helper.AppConstants.fullScreenHeight;
import static edu.cs424.data.helper.AppConstants.fullScreenWidth;
import omicronAPI.OmicronAPI;
import omicronAPI.OmicronTouchListener;
import processing.core.PApplet;
import edu.cs424.traffic.components.MainPanel;
import edu.cs424.traffic.components.MainPanel.MouseMovements;

public class Main extends PApplet implements OmicronTouchListener
{
	static Main main;
	OmicronAPI omicronManager;
	//TouchListener touchListener;

	int width = 1360,height = 384;
	SettingsLoader sl;
	MainPanel mainPanel;

	public static void main(String args[])
	{
	    PApplet.main(new String[] { edu.cs424.traffic.central.Main.class.getName() });
	}

	@Override
	public void init()
	{
		super.init();
		System.out.println("Main.init()");
		initProcedure();
		System.out.println("Main.init() over");
	}
	
	private void initProcedure()
	{
		this.main = this;
		sl = SettingsLoader.getInstance(this);
		omicronManager = new OmicronAPI(this);

		if (sl.getConfigValueAsBoolean(EnumConfig.ONWALL)) 
		{
			
			omicronManager.setFullscreen(true);
			width = fullScreenWidth * 6;
			height = fullScreenHeight * 6;
		}
		else
		{
			System.out.println("Main.initProcedure()" + "NOT ON wall");
			width = fullScreenWidth * SettingsLoader.scaleFactor;
			height = fullScreenHeight * SettingsLoader.scaleFactor;
		}
	}


	@Override
	public void setup()
	{	
		System.out.println("Main.setup() started");
		
		if(sl == null)
			initProcedure();
		
		size(width, height,sl.getConfigValueAsString(EnumConfig.RENDERER));			

		if (sl.getConfigValueAsBoolean(EnumConfig.ONWALL)) 
		{
			omicronManager.ConnectToTracker(7001, 7340, "131.193.77.159");
			System.out.println("Main.setup() " + "on wall");
		}
		
		//touchListener = new TouchListener(this);
		omicronManager.setTouchListener(this);		
		mainPanel = new MainPanel(0, 0, width, height, 0, 0);
		mainPanel.setup();
		smooth();
		frameRate(10);
		
		System.out.println("Main.setup()" + "stopped");
		
	}


	@Override
	public void draw() 
	{			
		mainPanel.draw();
		omicronManager.process();
	}
	
	
	public static PApplet getPapplet()
	{
		return main;
	}


	@Override
	public void touchDown(int ID, float xPos, float yPos, float xWidth, float yWidth) 
	{
		mainPanel.touch(ID,xPos, yPos, MouseMovements.MOUSEDOWN);
	}
	@Override
	public void touchMove(int ID, float xPos, float yPos, float xWidth, float yWidth) 
	{
		mainPanel.touch(ID,xPos, yPos, MouseMovements.MOUSEMOVE);
	}
	@Override
	public void touchUp(int ID, float xPos, float yPos, float xWidth, float yWidth) 
	{
		mainPanel.touch(ID,xPos, yPos, MouseMovements.MOUSEUP);
	}

}
