package edu.cs424.traffic.map.dataset;

public class CrashInfo {

	String date, state, weather, numOfDeaths, driverAge, driverSex, dui, speeding, vehicleType, onHighway, mannerOfCollision;
	
	public CrashInfo() {
		
	}
	
	public String toString() {
		StringBuffer infoString = new StringBuffer("");
		infoString.append("Date: " + this.getDate() + "\n");
		infoString.append("State: " + this.getState() + "\n");
		infoString.append("Deaths: " + this.getNumOfDeaths() + "\n");
		infoString.append("Driver Age: " + this.getDriverAge() + "\n");
		infoString.append("Driver Sex: " + this.getDriverSex() + "\n");
		infoString.append("DUI: " + this.getDui() + "\n");
		infoString.append("Speeding: " + this.getSpeeding() + "\n");
		infoString.append("Weather: " + this.getWeather() + "\n");
		infoString.append("Collision Type: " + this.getMannerOfCollision() + "\n");
		infoString.append("Vehicle Type: " + this.getVehicleType() + "\n");
		infoString.append("On Highway: " + this.getOnHighway());
		
		return infoString.toString();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getNumOfDeaths() {
		return numOfDeaths;
	}

	public void setNumOfDeaths(String numOfDeaths) {
		this.numOfDeaths = numOfDeaths;
	}

	public String getDriverAge() {
		return driverAge;
	}

	public void setDriverAge(String driverAge) {
		this.driverAge = driverAge;
	}

	public String getDriverSex() {
		return driverSex;
	}

	public void setDriverSex(String driverSex) {
		this.driverSex = driverSex;
	}

	public String getDui() {
		return dui;
	}

	public void setDui(String dui) {
		this.dui = dui;
	}

	public String getSpeeding() {
		return speeding;
	}

	public void setSpeeding(String speeding) {
		this.speeding = speeding;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getOnHighway() {
		return onHighway;
	}

	public void setOnHighway(String onHighway) {
		this.onHighway = onHighway;
	}

	public String getMannerOfCollision() {
		return mannerOfCollision;
	}

	public void setMannerOfCollision(String mannerOfCollision) {
		this.mannerOfCollision = mannerOfCollision;
	}
}
