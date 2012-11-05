package edu.cs424.traffic.central;

import omicronAPI.OmicronTouchListener;

public class TouchListener implements OmicronTouchListener {

  private Main main;

  public TouchListener(Main main) {
    this.main = main;
  }

  @Override
  public void touchDown(int ID, float xPos, float yPos, float xWidth, float yWidth) {
    main.touchDown(ID, xPos, yPos, xWidth, yWidth);
  }
  
  @Override
  public void touchMove(int ID, float xPos, float yPos, float xWidth, float yWidth) {
    main.touchMove(ID, xPos, yPos, xWidth, yWidth);
  }
  @Override
  public void touchUp(int ID, float xPos, float yPos, float xWidth, float yWidth) {
    main.touchUp(ID, xPos, yPos, xWidth, yWidth);
  }
}
