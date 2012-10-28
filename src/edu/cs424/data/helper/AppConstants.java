package edu.cs424.data.helper;

public class AppConstants {
    
	//Main Title
	public static int titleFontSize = 24;
	public static int titleX = 20;
	public static int titleY = 15;
	
	//Year title (if required)
	public static int yearFontSize = 20;
	public static int yearX = 645;
	public static int yearY = titleY + titleFontSize - yearFontSize; //to get same baseline
	
	//Map panel
	public static int mapX = 40;
	public static int mapY = 60;
	public static int mapWidth = 620;
	public static int mapHeight = 280;
	
	//Graph(1) panel
	public static int graph1X = 660;
	public static int graph1Y = 20;
	public static int graph1Width = 310;
	public static int graph1Height = 180;
	
	public static int graphAxisX = 20;
	public static int graphAxisY = 20;
	public static int graphAxisWidth = 280;
	public static int graphAxisHeight = 140;
	
	
	
	public static int graph1TitleX = 740;
	public static int graph1TitleY = 26;
	
	public static String graph1PlotColor = "";
	public static int graph1ClearButtonX = 1003;
	public static int graph1ClearButtonY = 188;
	
	//Graph(2) panel
	public static int graph2X = 1010;
	public static int graph2Y = 20;
	public static int graph2Width = 310;
	public static int graph2Height = 180;
	
	public static int graph2TitleX = 1060;
	public static int graph2TitleY = 26;
	
	public static String graph2PlotColor = "";
	public static int graph2ClearButtonX = 1003;
	public static int graph2ClearButtonY = 188;
	
	//graph title
	public static int graphTitleFontSize = 10;
	
	//Buttons to clear selected filters from graph
	public static int graphClearButtonWidth = 10;
	public static int graphClearButtonHeight = 12;
		
	
	
	
	//Tab text panel (Place tab names inside this)
	public static int tabPanelX = 660;
	public static int tabPanelY = 225;
	public static int tabPanelWidth = 340;//a bit too optimistically wide
	public static int tabPanelHeight = 25;
	//font size 16
	
	//Main control panel below Tabs
	public static int controlPanelX = 660;
	public static int controlPanelY = 250;
	public static int controlPanelWidth = 660;
	public static int controlPanelHeight = 130;
	
	//Buttons to select (filters for) graph
	public static int graph1ButtonX = 0;
	public static int graph1ButtonY = 32;
	public static int graph2ButtonX = 0;
	public static int graph2ButtonY = 55;
	public static int graphButtonHeight = 20;
	public static int graphButtonWidth = 60;
	
	public static int filterHolderX = 80 ;
	public static int filterHolderY = 15;
	public static int filterHolderHeight = 115;
	public static int filterHolderWidth = 580;
	
	//Filter categories panel with all the big buttons
//	public static int filtersX = 3;
//	public static int filtersY = 17;
//	public static int filtersWidth = 260;
//	public static int filtersHeight = 100;
	
	public static int filterButtonX = 3; //inside above panel, x=3, y=2, width=60, height=20
	public static int filterButtonY = 17;
		
	//Filter values panel
	public static int filterValuesX = 280;
	public static int filterValuesY = 15;
	public static int filterValuesWidth = 180;
	public static int filterValuesHeight = 100;
	
	public static int filterValueButtonX = 285; //inside above panel, x=5, y=10, width=82, height=12
	public static int filterValueButtonY = 25;
	
	//All selected filter values panel
	public static int selectedValuesX = 480;
	public static int selectedValuesY = 15;
	public static int selectedValuesWidth = 100;
	public static int selectedValuesHeight = 100;
	
	
	
	
	
	//Buttons
	public static String buttonSelectedColor = "#015744";
	public static String buttonUnselectedColor = "#052E25";
	
	//General font color
	public static String textSelectedColor = "#D8D8D8";
	public static String textUnselectedColor = "#898989";
	
}