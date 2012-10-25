//package cs422.project2.sketch;
//
//import java.util.ArrayList;
//
//import omicronAPI.OmicronTouchListener;
//import processing.core.PApplet;
//import cs422.project2.Constants;
//import cs422.project2.listeners.YearChangeListener;
//import cs422.project2.sketch.data.Variable;
//
//public class SliderSketch extends Sketch implements OmicronTouchListener{
//
//	protected float sliderY1, sliderY2;
//	protected float markingSize;
//	protected float leftYear, rightYear;
//	protected float triSize = (Constants.SCALE * 5);
//	protected float triColor = 100;
//	
//	protected float txtYearX, txtSize;
//	protected ArrayList<YearChangeListener> listeners; 
//	
//	public SliderSketch(Variable data) {
//		super(data);
//		sliderY1 = plotY1 + (Constants.SCALE * 20);
//		sliderY2 = plotY2 - (Constants.SCALE * 20);
//		leftYear = Constants.YEAR_MIN;
//		rightYear = Constants.YEAR_MAX;
//		txtYearX = (Constants.SCALE * 30);
//		txtSize = (Constants.SCALE * 15);
//		listeners = new ArrayList<YearChangeListener>();
//	}
//	
//	@Override
//	public void display() {
//		super.display();
//		if(!isActive) return;
//		parent.pushStyle();
//		
//		// try to put all the literal values in Constants file
//		parent.fill(Constants.GREY);
//		parent.rect(plotX1, plotY1, plotX2, plotY2);
//		
//		parent.fill(224);
//		parent.rect(plotX1, sliderY1, plotX2, sliderY2);
//		drawYearMarkings();
//		drawTriangles();
//		drawYearLabels();
//		parent.popStyle();
//	}
//	
//	public void drawYearMarkings(){
//		parent.stroke(0);
//		for(int y = Constants.YEAR_MIN; y <= Constants.YEAR_MAX; y++){
//			float x = PApplet.map(y, Constants.YEAR_MIN, Constants.YEAR_MAX, plotX1, plotX2);
//			if (y % Constants.YEAR_INTERVAL == 0) {
//				parent.line(x, plotY1, x, plotY1 + Constants.majorMark);
//				parent.line(x, plotY2, x, plotY2 - Constants.majorMark);
//			}
//			else{
//				parent.line(x, plotY1, x, plotY1 + Constants.minorMark);
//				parent.line(x, plotY2, x, plotY2 - Constants.minorMark);
//			}
//			
//		}
//	}
//	
//	public void drawTriangles(){
//		float leftX = PApplet.map(leftYear, Constants.YEAR_MIN, Constants.YEAR_MAX, plotX1, plotX2);
//		float rightX = PApplet.map(rightYear, Constants.YEAR_MIN, Constants.YEAR_MAX, plotX1, plotX2);
//		
//		// draw left triangle
//		parent.fill(triColor);
//		parent.strokeWeight((1*Constants.SCALE));
//		parent.triangle(leftX, sliderY1, leftX - triSize, sliderY2, leftX + triSize, sliderY2);
//		parent.triangle(rightX, sliderY2, rightX - triSize, sliderY1, rightX + triSize, sliderY1);
//		
//		parent.fill(255);
//		parent.noStroke();
//		parent.beginShape();
//			parent.vertex(leftX, sliderY1);
//			parent.vertex(rightX - triSize, sliderY1);
//			parent.vertex(rightX, sliderY2);
//			parent.vertex(leftX + triSize, sliderY2);
//		parent.endShape();
//	}
//	
//	public void drawYearLabels(){
//		float y = (plotY2 - plotY1)/2;
//		y += plotY1;
//		parent.fill(255);
//		parent.textSize(txtSize);
//		parent.textAlign(PApplet.CENTER);
//		parent.text(PApplet.nf(this.leftYear, 0, 0), plotX1 - txtYearX, y + parent.textDescent());
//		parent.text(PApplet.nf(this.rightYear, 0, 0), plotX2 + txtYearX, y + parent.textDescent());
//	
//	}
//	
//	@Override
//	public void touchDown(int ID, float xPos, float yPos, float xWidth, float yWidth){
//		if( !(xPos > plotX1 && xPos < plotX2 && yPos > plotY1 && yPos < plotY2) ) {
//			//initializeGraph();
//			return;
//		}
//		
//		// check if touch on the top bar
//		if(yPos < sliderY1){
//			int year = Math.round(PApplet.map(xPos, plotX1, plotX2, Constants.YEAR_MIN, Constants.YEAR_MAX));
//			if(year < rightYear && year != leftYear){
//				leftYear = year;
//				updateListeners();
//			}
//		}
//		else if(yPos > sliderY2){
//			int year = Math.round(PApplet.map(xPos, plotX1, plotX2, Constants.YEAR_MIN, Constants.YEAR_MAX));
//			if(year > leftYear && year != rightYear){
//				rightYear = year;
//				updateListeners();
//			}
//		}
//		else {
//			return;
//		}
//		
//	}
//
//	public void updateListeners(){
//		// update listeners
//		for(int i = 0; i < listeners.size(); i++){
//			listeners.get(i).yearChanged((int)leftYear, (int)rightYear);
//		}
//	}
//
//	@Override
//	public void touchMove(int ID, float xPos, float yPos, float xWidth, float yWidth){
//		
//	}
//
//	@Override
//	public void touchUp(int ID, float xPos, float yPos, float xWidth, float yWidth){
//		
//	}
//	
//	public void setYearChangeListener(YearChangeListener listener){
//		this.listeners.add(listener);
//	}
//	
//	
//}
