package edu.cs424.traffic.central;



public interface TouchEnabled {
	
	  public boolean touch(float x, float y, boolean down);

	  public boolean containsPoint(float x, float y);

}
