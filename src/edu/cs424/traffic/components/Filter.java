package edu.cs424.traffic.components;

import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.gui.Button;

public class Filter extends Panel implements TouchEnabled
{
	FilterButton[] buttons;

	public Filter(float x0, float y0, float width, float height,
			float parentX0, float parentY0)
	{
		super(x0, y0, width, height, parentX0, parentY0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setup()
	{
		buttons = new FilterButton[16];
		int count = 0;
		for ( int i = 0 ; i<4 ; i++ )
		{
			for ( int j = 0 ; j<4 ; j++ )
			{
				buttons[count] = new FilterButton(i*65, j*25, 60, 20, x0, y0, "text " +count, this);
				addTouchSubscriber(buttons[count]);
				count++;
			}
		}
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) 
	{
		propagateTouch(x, y, event);
		return false;
	}


	@Override
	public void draw() 
	{
		if(needRedraw)
		{
			System.out.println("Filter.draw()");
			for(Button button : buttons)
				button.draw();
					
			needRedraw = false;

		}
	}

	public void handleButtonClicks(String buttontext,boolean isSelected)
	{
		setReDraw();
		System.out.println("Filter.handleButtonClicks() " + buttontext);
	}




}
