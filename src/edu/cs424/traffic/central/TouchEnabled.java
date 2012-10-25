package edu.cs424.traffic.central;

import edu.cs424.traffic.components.MainPanel.MouseMovements;



public interface TouchEnabled {
	
	  public boolean touch(float x, float y, MouseMovements event);

	  public boolean containsPoint(float x, float y);

}
