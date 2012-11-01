package edu.cs424.traffic.map.dataset;

import java.util.HashMap;

import com.modestmaps.geo.Location;

/**
 * Class with all the lat-longs of 50 states
 */
public class StateLatLon {

	private HashMap<Integer, Location> states = new HashMap<Integer, Location>();
	private String geocodeURL = "http://maps.googleapis.com/maps/api/geocode/json?address=madison&sensor=false";
	
	public StateLatLon() {
		this.states.put(2, new Location(61.3850f,-152.2683f));
		this.states.put(1, new Location(32.7990f, -86.8073f));
		this.states.put(5, new Location(34.9513f, -92.3809f));
		this.states.put(4, new Location(33.7712f, -111.3877f));
		this.states.put(6, new Location(36.1700f, -119.7462f));
		this.states.put(8, new Location(39.0646f, -105.3272f));
		this.states.put(9, new Location(41.5834f, -72.7622f));
		this.states.put(10, new Location(39.3498f, -75.5148f));
		this.states.put(12, new Location(27.8333f, -81.7170f));
		this.states.put(13, new Location(32.9866f, -83.6487f));
		this.states.put(15, new Location(21.1098f, -157.5311f));
		this.states.put(19, new Location(42.0046f, -93.2140f));
		this.states.put(16, new Location(44.2394f, -114.5103f));
		this.states.put(17, new Location(40.3363f, -89.0022f));
		this.states.put(18, new Location(39.8647f, -86.2604f));
		this.states.put(20, new Location(38.5111f, -96.8005f));
		this.states.put(21, new Location(37.6690f, -84.6514f));
		this.states.put(22, new Location(31.1801f, -91.8749f));
		this.states.put(25, new Location(42.2373f, -71.5314f));
		this.states.put(24, new Location(39.0724f, -76.7902f));
		this.states.put(23, new Location(44.6074f, -69.3977f));
		this.states.put(26, new Location(43.3504f, -84.5603f));
		this.states.put(27, new Location(45.7326f, -93.9196f));
		this.states.put(29, new Location(38.4623f, -92.3020f));
		this.states.put(28, new Location(32.7673f, -89.6812f));
		this.states.put(30, new Location(46.9048f, -110.3261f));
		this.states.put(37, new Location(35.6411f, -79.8431f));
		this.states.put(38, new Location(47.5362f, -99.7930f));
		this.states.put(31, new Location(41.1289f, -98.2883f));
		this.states.put(33, new Location(43.4108f, -71.5653f));
		this.states.put(34, new Location(40.3140f, -74.5089f));
		this.states.put(35, new Location(34.8375f, -106.2371f));
		this.states.put(32, new Location(38.4199f, -117.1219f));
		this.states.put(36, new Location(42.1497f, -74.9384f));
		this.states.put(39, new Location(40.3736f, -82.7755f));
		this.states.put(40, new Location(35.5376f, -96.9247f));
		this.states.put(41, new Location(44.5672f, -122.1269f));
		this.states.put(42, new Location(40.5773f, -77.2640f));
		this.states.put(44, new Location(41.6772f, -71.5101f));
		this.states.put(45, new Location(33.8191f, -80.9066f));
		this.states.put(46, new Location(44.2853f, -99.4632f));
		this.states.put(47, new Location(35.7449f, -86.7489f));
		this.states.put(48, new Location(31.1060f, -97.6475f));
		this.states.put(49, new Location(40.1135f, -111.8535f));
		this.states.put(51, new Location(37.7680f, -78.2057f));
		this.states.put(78, new Location(18.0001f, -64.8199f));
		this.states.put(50, new Location(44.0407f, -72.7093f));
		this.states.put(53, new Location(47.3917f, -121.5708f));
		this.states.put(55, new Location(44.2563f, -89.6385f));
		this.states.put(54, new Location(38.4680f, -80.9696f));
		this.states.put(56, new Location(42.7475f, -107.2085f));
	}
	
	public HashMap<Integer, Location> getStates() {
		return this.states;
	}
	
}
