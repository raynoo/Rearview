package edu.cs424.traffic.central;

public enum EnumConfig 
{
	
ONWALL("onwall"),
ZOOMFACTOR("zoomfactor");


String propName;

private EnumConfig(String propName)
{
	this.propName = propName;
}

public String getPropName()
{
	return propName;
}


}
