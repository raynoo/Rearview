package edu.cs424.traffic.central;

import omicronAPI.OmicronTouchListener;

public class TouchListener implements OmicronTouchListener {

  private Main p;

  public TouchListener(Main p) {
    this.p = p;
  }

  @Override
  public void touchDown(int ID, float xPos, float yPos, float xWidth, float yWidth) {
    p.touchDown(ID, xPos, yPos, xWidth, yWidth);
  }
  
  @Override
  public void touchMove(int ID, float xPos, float yPos, float xWidth, float yWidth) {
    p.touchMove(ID, xPos, yPos, xWidth, yWidth);
  }
  @Override
  public void touchUp(int ID, float xPos, float yPos, float xWidth, float yWidth) {
    p.touchUp(ID, xPos, yPos, xWidth, yWidth);
  }
}
