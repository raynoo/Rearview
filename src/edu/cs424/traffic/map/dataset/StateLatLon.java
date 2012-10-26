package edu.cs424.traffic.map.dataset;

import java.util.HashMap;

/**
 * Class with all the lat-longs of 50 states
 */
public class StateLatLon {

	private HashMap<String, MapLocation> states = new HashMap<String, MapLocation>();
	private String geocodeURL = "http://maps.googleapis.com/maps/api/geocode/json?address=madison&sensor=false";
	
	public StateLatLon() {
		this.states.put("AK", new MapLocation(61.3850f,-152.2683f));
		this.states.put("AL", new MapLocation(32.7990f, -86.8073f));
		this.states.put("AR", new MapLocation(34.9513f, -92.3809f));
		this.states.put("AZ", new MapLocation(33.7712f, -111.3877f));
		this.states.put("CA", new MapLocation(36.1700f, -119.7462f));
		this.states.put("CO", new MapLocation(39.0646f, -105.3272f));
		this.states.put("CT", new MapLocation(41.5834f, -72.7622f));
		this.states.put("DE", new MapLocation(39.3498f, -75.5148f));
		this.states.put("FL", new MapLocation(27.8333f, -81.7170f));
		this.states.put("GA", new MapLocation(32.9866f, -83.6487f));
		this.states.put("HI", new MapLocation(21.1098f, -157.5311f));
		this.states.put("IA", new MapLocation(42.0046f, -93.2140f));
		this.states.put("ID", new MapLocation(44.2394f, -114.5103f));
		this.states.put("IL", new MapLocation(40.3363f, -89.0022f));
		this.states.put("IN", new MapLocation(39.8647f, -86.2604f));
		this.states.put("KS", new MapLocation(38.5111f, -96.8005f));
		this.states.put("KY", new MapLocation(37.6690f, -84.6514f));
		this.states.put("LA", new MapLocation(31.1801f, -91.8749f));
		this.states.put("MA", new MapLocation(42.2373f, -71.5314f));
		this.states.put("MD", new MapLocation(39.0724f, -76.7902f));
		this.states.put("ME", new MapLocation(44.6074f, -69.3977f));
		this.states.put("MI", new MapLocation(43.3504f, -84.5603f));
		this.states.put("MN", new MapLocation(45.7326f, -93.9196f));
		this.states.put("MO", new MapLocation(38.4623f, -92.3020f));
		this.states.put("MS", new MapLocation(32.7673f, -89.6812f));
		this.states.put("MT", new MapLocation(46.9048f, -110.3261f));
		this.states.put("NC", new MapLocation(35.6411f, -79.8431f));
		this.states.put("ND", new MapLocation(47.5362f, -99.7930f));
		this.states.put("NE", new MapLocation(41.1289f, -98.2883f));
		this.states.put("NH", new MapLocation(43.4108f, -71.5653f));
		this.states.put("NJ", new MapLocation(40.3140f, -74.5089f));
		this.states.put("NM", new MapLocation(34.8375f, -106.2371f));
		this.states.put("NV", new MapLocation(38.4199f, -117.1219f));
		this.states.put("NY", new MapLocation(42.1497f, -74.9384f));
		this.states.put("OH", new MapLocation(40.3736f, -82.7755f));
		this.states.put("OK", new MapLocation(35.5376f, -96.9247f));
		this.states.put("OR", new MapLocation(44.5672f, -122.1269f));
		this.states.put("PA", new MapLocation(40.5773f, -77.2640f));
		this.states.put("RI", new MapLocation(41.6772f, -71.5101f));
		this.states.put("SC", new MapLocation(33.8191f, -80.9066f));
		this.states.put("SD", new MapLocation(44.2853f, -99.4632f));
		this.states.put("TN", new MapLocation(35.7449f, -86.7489f));
		this.states.put("TX", new MapLocation(31.1060f, -97.6475f));
		this.states.put("UT", new MapLocation(40.1135f, -111.8535f));
		this.states.put("VA", new MapLocation(37.7680f, -78.2057f));
		this.states.put("VI", new MapLocation(18.0001f, -64.8199f));
		this.states.put("VT", new MapLocation(44.0407f, -72.7093f));
		this.states.put("WA", new MapLocation(47.3917f, -121.5708f));
		this.states.put("WI", new MapLocation(44.2563f, -89.6385f));
		this.states.put("WV", new MapLocation(38.4680f, -80.9696f));
		this.states.put("WY", new MapLocation(42.7475f, -107.2085f));
	}
	
	public HashMap<String, MapLocation> getStates() {
		return this.states;
	}
	
}
