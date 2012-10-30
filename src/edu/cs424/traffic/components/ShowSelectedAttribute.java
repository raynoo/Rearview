package edu.cs424.traffic.components;

import java.util.ArrayList;

import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.gui.Button;

public class ShowSelectedAttribute extends Panel implements TouchEnabled
{
	FilterValuePanel filterValuePanel;
	FilterPanel filterPanel;
	Button up,down;

	int startPoint = 0;

	private ArrayList<SelectedAttrButton> buttonList = new ArrayList<SelectedAttrButton>();

	public ShowSelectedAttribute(float x0, float y0, float width, float height,
			float parentX0, float parentY0,FilterValuePanel filterValuePanel,FilterPanel filterPanel) {

		super(x0, y0, width, height, parentX0, parentY0);
		this.filterValuePanel = filterValuePanel;
		this.filterPanel = filterPanel;
	}

	public void setup() {

		up = new Button(5, 3+ 15*5 + 3, 38, 12, x0, y0, "UP", true);
		down = new Button(48, 3+ 15*5 + 3, 38, 12, x0, y0, "DOWN", true);

	}

	@Override
	public void draw()
	{
		if(needRedraw)
		{
			int i = 0;
			buttonList.clear();
			removeAllTouchSuscribers();
			background(EnumColor.GOLD);

			int count = 0;
			for(String key : filterValuePanel.buttons.keySet())
			{
				ArrayList<FilterValueButton> subButtonList = filterValuePanel.buttons.get(key);
				for(FilterValueButton button : subButtonList )
				{
					if( button.isPressed )
					{
						count++;
					}

					if( button.isPressed && i < 5 && count >= startPoint)
					{
						SelectedAttrButton temp = new SelectedAttrButton(5, 3 + i*15, 82, 12, x0, y0,key + " = " + button.text,this);
						addTouchSubscriber(temp);
						temp.draw();
						buttonList.add(temp);
						i++;
					}
				}				
			}

			up.draw();
			down.draw();
			needRedraw = false;
		}
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event)
	{
		if(event == MouseMovements.MOUSEDOWN)
		{
			if( up.containsPoint(x, y) )
			{
				startPoint++;
			}
			else if( down.containsPoint(x, y) )
			{
				if(startPoint > 0)
					startPoint--;
			}
			else
				propagateTouch(x, y, event);
		}
		return false;
	}

	public void removeAttribute(String text)
	{
		String value[] = text.split(" = ");
		setReDraw();
		filterPanel.selectDeselectbutton(value[0], value[1], false);

	}

	public void forceRedrawAllComponents()
	{
		for(SelectedAttrButton temp : buttonList)
		{
			temp.setReDraw();
		}
		up.setReDraw();
		down.setReDraw();
		setReDraw();
	}

}
