package edu.cs424.traffic.components;

import static edu.cs424.data.helper.AppConstants.updateButtonHeight;
import static edu.cs424.data.helper.AppConstants.updateButtonWidth;
import static edu.cs424.data.helper.AppConstants.updateButtonX;
import static edu.cs424.data.helper.AppConstants.updateButtonY;
import edu.cs424.data.helper.AppConstants;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import edu.cs424.traffic.gui.Button;
import edu.cs424.traffic.pubsub.PubSub;
import edu.cs424.traffic.pubsub.PubSub.Event;
import edu.cs424.traffic.pubsub.Suscribe;

public class FilterHolder extends Panel implements TouchEnabled,Suscribe
{
	public FilterPanel filter;
	public FilterValuePanel filterValues;
	public ShowSelectedAttribute showSelected;
	public Tab1 tab;
	Event toPublish;


	public FilterHolder(float x0, float y0, float width, float height,
			float parentX0, float parentY0,Tab1 tab,Event toPublish) {
		super(x0, y0, width, height, parentX0, parentY0);

		this.tab = tab;
		this.toPublish = toPublish;
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean touch(float x, float y, MouseMovements event) {

		propagateTouch(x, y, event);		
		return false;
	}

	public void  updateFilter(Event event)
	{
		filter.updateFilter(event);
	}

	public void setup() 
	{
		PubSub.subscribeEvent(Event.CLEAR_FILTER,this);

		filter = new FilterPanel(AppConstants.filterButtonX, AppConstants.filterButtonY, 255, 95, x0, y0,this);
		filter.setup();
		addTouchSubscriber(filter);

		filterValues = new FilterValuePanel(AppConstants.filterValuesX, AppConstants.filterValuesY, AppConstants.filterValuesWidth,
				AppConstants.filterValuesHeight, x0, y0,this);
		filterValues.setup();
		addTouchSubscriber(filterValues);

		showSelected = new ShowSelectedAttribute(AppConstants.selectedValuesX, AppConstants.filterValuesY,
				AppConstants.selectedValuesWidth, AppConstants.selectedValuesHeight, x0, y0 , filterValues,filter);
		showSelected.setup();
		addTouchSubscriber(showSelected);
	}

	@Override
	/*
	 * This draw has to make sure all the buttons are redrawn
	 * */
	public void draw()
	{
		if(needRedraw)
		{
			filter.draw();
			filterValues.draw();
			showSelected.draw();			
			needRedraw = false;
		}
	}

	public void forceRedraw()
	{
		filter.forceRedrawAllComponents();
		filterValues.forceRedrawAllComponents();
		showSelected.forceRedrawAllComponents();
		needRedraw = true;
	}
	@Override
	public void receiveNotification(Event eventName, Object... object) 
	{
		if(eventName == Event.CLEAR_FILTER)
		{
			if( object[0] == toPublish)
			{
				// call the selected value panel to clear the data
				showSelected.clearPanel();
				//updateFilter(toPublish);
			}
		}

	}

}
