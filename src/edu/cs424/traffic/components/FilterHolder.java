package edu.cs424.traffic.components;

import edu.cs424.data.helper.AppConstants;
import edu.cs424.traffic.central.EnumColor;
import edu.cs424.traffic.central.Panel;
import edu.cs424.traffic.central.TouchEnabled;
import edu.cs424.traffic.components.MainPanel.MouseMovements;
import static edu.cs424.data.helper.AppConstants.*;

public class FilterHolder extends Panel implements TouchEnabled
{
	public FilterPanel filter;
	public FilterValuePanel filterValues;
	public ShowSelectedAttribute showSelected;

	public FilterHolder(float x0, float y0, float width, float height,
			float parentX0, float parentY0) {
		super(x0, y0, width, height, parentX0, parentY0);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean touch(float x, float y, MouseMovements event) {
		propagateTouch(x, y, event);
		return false;
	}
	@Override
	public void setup() 
	{
		filter = new FilterPanel(AppConstants.filterButtonX, AppConstants.filterButtonY, 255, 95, x0, y0,this);
		addTouchSubscriber(filter);

		filterValues = new FilterValuePanel(AppConstants.filterValuesX, AppConstants.filterValuesY, AppConstants.filterValuesWidth,
				AppConstants.filterValuesHeight, x0, y0,this);
		addTouchSubscriber(filterValues);

		showSelected = new ShowSelectedAttribute(AppConstants.selectedValuesX, AppConstants.filterValuesY,
				AppConstants.selectedValuesWidth, AppConstants.selectedValuesHeight, x0, y0 , filterValues,filter);
		addTouchSubscriber(showSelected);

	}

	@Override
	/*
	 * This draw has to make sure all the buttons are redrawn
	 * */
	public void draw()
	{
		filter.draw();
		filterValues.draw();
		showSelected.draw();
	}

	public void forceRedraw()
	{
		background(EnumColor.GOLD);
		filter.forceRedrawAllComponents();
		filterValues.forceRedrawAllComponents();
		showSelected.forceRedrawAllComponents();
	}

}
