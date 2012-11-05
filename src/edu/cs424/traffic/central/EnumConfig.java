package edu.cs424.traffic.central;

public enum EnumConfig 
{
	
ONWALL("onwall"),
SCALEFACTOR("scalefactor"),
RENDERER("renderer"),
REPEATDRAW("repeatdraw");


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
