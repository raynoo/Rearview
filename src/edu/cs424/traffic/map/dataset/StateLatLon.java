package edu.cs424.traffic.map.dataset;

import java.util.HashMap;

import com.modestmaps.geo.Location;

/**
 * Class with all the lat-longs of 50 states
 */
public class StateLatLon {

	private HashMap<Location, Integer> states = new HashMap<Location, Integer>();
	private String geocodeURL = "http://maps.googleapis.com/maps/api/geocode/json?address=madison&sensor=false";
	
	public StateLatLon() {
		this.states.put(new Location(61.3850f,-152.2683f), 829);
		this.states.put(new Location(32.7990f, -86.8073f), 23);
		this.states.put(new Location(34.9513f, -92.3809f), 92);
		this.states.put(new Location(33.7712f, -111.3877f), 263);
		this.states.put(new Location(36.1700f, -119.7462f), 451);
		this.states.put(new Location(39.0646f, -105.3272f), 777);
		this.states.put(new Location(41.5834f, -72.7622f), 65);
		this.states.put(new Location(39.3498f, -75.5148f), 23);
		this.states.put(new Location(27.8333f, -81.7170f), 16);
		this.states.put(new Location(32.9866f, -83.6487f), 45);
		this.states.put(new Location(21.1098f, -157.5311f), 832);
		this.states.put(new Location(42.0046f, -93.2140f), 490);
		this.states.put(new Location(44.2394f, -114.5103f), 34);
		this.states.put(new Location(40.3363f, -89.0022f), 74);
		this.states.put(new Location(39.8647f, -86.2604f), 623);
		this.states.put(new Location(38.5111f, -96.8005f), 287);
		this.states.put(new Location(37.6690f, -84.6514f), 655);
		this.states.put(new Location(31.1801f, -91.8749f), 147);
		this.states.put(new Location(42.2373f, -71.5314f), 619);
		this.states.put(new Location(39.0724f, -76.7902f), 340);
		this.states.put(new Location(44.6074f, -69.3977f), 51);
		this.states.put(new Location(43.3504f, -84.5603f), 98);
		this.states.put(new Location(45.7326f, -93.9196f), 45);
		this.states.put(new Location(38.4623f, -92.3020f), 614);
		this.states.put(new Location(32.7673f, -89.6812f), 94);
		this.states.put(new Location(46.9048f, -110.3261f), 34);
		this.states.put(new Location(35.6411f, -79.8431f), 619);
		this.states.put(new Location(47.5362f, -99.7930f), 100);
		this.states.put(new Location(41.1289f, -98.2883f), 325);
		this.states.put(new Location(43.4108f, -71.5653f), 9);
		this.states.put(new Location(40.3140f, -74.5089f), 34);
		this.states.put(new Location(34.8375f, -106.2371f), 222);
		this.states.put(new Location(38.4199f, -117.1219f), 456);
		this.states.put(new Location(42.1497f, -74.9384f), 123);
		this.states.put(new Location(40.3736f, -82.7755f), 576);
		this.states.put(new Location(35.5376f, -96.9247f), 34);
		this.states.put(new Location(44.5672f, -122.1269f), 80);
		this.states.put(new Location(40.5773f, -77.2640f), 120);
		this.states.put(new Location(41.6772f, -71.5101f), 984);
		this.states.put(new Location(33.8191f, -80.9066f), 11);
		this.states.put(new Location(44.2853f, -99.4632f), 234);
		this.states.put(new Location(35.7449f, -86.7489f), 43);
		this.states.put(new Location(31.1060f, -97.6475f), 99);
		this.states.put(new Location(40.1135f, -111.8535f), 475);
		this.states.put(new Location(37.7680f, -78.2057f), 192);
		this.states.put(new Location(18.0001f, -64.8199f), 56);
		this.states.put(new Location(44.0407f, -72.7093f), 10);
		this.states.put(new Location(47.3917f, -121.5708f), 2);
		this.states.put(new Location(44.2563f, -89.6385f), 43);
		this.states.put(new Location(38.4680f, -80.9696f), 234);
		this.states.put(new Location(42.7475f, -107.2085f), 54);
	}
	
	public HashMap<Location, Integer> getStates() {
		return this.states;
	}
	
}
