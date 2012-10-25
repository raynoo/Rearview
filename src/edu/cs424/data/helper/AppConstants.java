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
	public static int graph1X = 740;
	public static int graph1Y = 60;
	public static int graph1Width = 260;
	public static int graph1Height = 140;
	
	public static int graph1TitleX = 740;
	public static int graph1TitleY = 26;
	
	public static String graph1PlotColor = "";
	public static int graph1ClearButtonX = 1003;
	public static int graph1ClearButtonY = 188;
	
	//Graph(2) panel
	public static int graph2X = 1060;
	public static int graph2Y = 60;
	public static int graph2Width = 260;
	public static int graph2Height = 140;
	
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
	
	//Buttons to select (filters for) graph
	public static int graph1ButtonX = 1260;
	public static int graph1ButtonY = 245; //width=60, height = 20
	public static int graph2ButtonX = 1260;
	public static int graph2ButtonY = 269;
	
	//Tab text panel //dimensions of first text only ("Filters")
	public static int tabPanelX = 740;
	public static int tabPanelY = 220;
	
	//Filter values panel 
	//@kevin: you'd probably want to divide width by 2 to have 2 columns
	public static int filterValuesX = 1060;
	public static int filterValuesY = 245;
	public static int filterValuesWidth = 180;
	public static int filterValuesHeight = 95;
	
	//Buttons
	public static String buttonSelectedColor = "#015744";
	public static String buttonUnselectedColor = "#052E25";
	
	//General font color
	public static String textSelectedColor = "#D8D8D8";
	public static String textUnselectedColor = "#898989";
	
}