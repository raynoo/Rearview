package edu.cs424.traffic.components;

import java.util.ArrayList;

import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;

public class ShowSelectedAttribute extends Panel implements TouchEnabled
{
	FilterValuePanel filterValuePanel;
	FilterPanel filterPanel;
	
	private ArrayList<SelectedAttrButton> buttonList = new ArrayList<SelectedAttrButton>();

	public ShowSelectedAttribute(float x0, float y0, float width, float height,
			float parentX0, float parentY0,FilterValuePanel filterValuePanel,FilterPanel filterPanel) {
		
		super(x0, y0, width, height, parentX0, parentY0);
		this.filterValuePanel = filterValuePanel;
		this.filterPanel = filterPanel;
	}

	public void setup() {


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

			for(String key : filterValuePanel.buttons.keySet())
			{
				ArrayList<FilterValueButton> subButtonList = filterValuePanel.buttons.get(key);
				for(FilterValueButton button : subButtonList )
				{
					if(button.isPressed && i < 6)
					{
						SelectedAttrButton temp = new SelectedAttrButton(5, 3 + i*15, 82, 12, x0, y0,key + " = " + button.text,this);
						addTouchSubscriber(temp);
						temp.draw();
						buttonList.add(temp);
						i++;
					}

				}
			}
			
			needRedraw = false;
		}
	}

	@Override
	public boolean touch(float x, float y, MouseMovements event) {
		propagateTouch(x, y, event);
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
		setReDraw();
	}

}
