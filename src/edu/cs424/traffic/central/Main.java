package edu.cs424.traffic.central;

import edu.cs424.traffic.components.MainPanel;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import omicronAPI.OmicronAPI;
import omicronAPI.OmicronTouchListener;
import processing.core.PApplet;

public class Main extends PApplet implements OmicronTouchListener
{
	static Main main;
	OmicronAPI omicronManager;
	TouchListener touchListener;

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
			width = 8160;
			height = 2304;
		}
		else
		{
			width = 1360 * SettingsLoader.scaleFactor;
			height = 384 * SettingsLoader.scaleFactor;
		}
	}


	@Override
	public void setup()
	{	
		if(sl == null)
			initProcedure();
		
		size(width, height,sl.getConfigValueAsString(EnumConfig.RENDERER));
				
		System.out.println("Main.setup()");

		if (sl.getConfigValueAsBoolean(EnumConfig.ONWALL)) 
		{
			omicronManager.ConnectToTracker(7001, 7340, "131.193.77.159");
			
		}
		touchListener = new TouchListener(this);
		omicronManager.setTouchListener(touchListener);
		
		mainPanel = new MainPanel(0, 0, width, height, 0, 0);
		smooth();
		frameRate(10);
		
	}


	@Override
	public void draw() 
	{	
		
		mainPanel.draw();
		omicronManager.process();
	}
	



	public void touchDown(int ID, float xPos, float yPos, float xWidth, float yWidth) 
	{
		mainPanel.touch(xPos, yPos, MouseMovements.MOUSEDOWN);
	}
	public void touchMove(int ID, float xPos, float yPos, float xWidth, float yWidth) 
	{
		mainPanel.touch(xPos, yPos, MouseMovements.MOUSEMOVE);
	}
	public void touchUp(int ID, float xPos, float yPos, float xWidth, float yWidth) 
	{
		mainPanel.touch(xPos, yPos, MouseMovements.MOUSEUP);
	}

}
