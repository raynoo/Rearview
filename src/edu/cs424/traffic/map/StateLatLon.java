package edu.cs424.traffic.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class StateLatLon {

	HashMap<String, LatLon> states = new HashMap<String, LatLon>();
	
	StateLatLon() {
		this.states.put("AK", new LatLon(61.3850,-152.2683));
		this.states.put("AL", new LatLon(32.7990, -86.8073));
		this.states.put("AR", new LatLon(34.9513, -92.3809));
		this.states.put("AS", new LatLon(14.2417, -170.7197));
		this.states.put("AZ", new LatLon(33.7712, -111.3877));
		this.states.put("CA", new LatLon(36.1700, -119.7462));
		this.states.put("CO", new LatLon(39.0646, -105.3272));
		this.states.put("CT", new LatLon(41.5834, -72.7622));
		this.states.put("DC", new LatLon(38.8964, -77.0262));
		this.states.put("DE", new LatLon(39.3498, -75.5148));
		this.states.put("FL", new LatLon(27.8333, -81.7170));
		this.states.put("GA", new LatLon(32.9866, -83.6487));
		this.states.put("HI", new LatLon(21.1098, -157.5311));
		this.states.put("IA", new LatLon(42.0046, -93.2140));
		this.states.put("ID", new LatLon(44.2394, -114.5103));
		this.states.put("IL", new LatLon(40.3363, -89.0022));
		this.states.put("IN", new LatLon(39.8647, -86.2604));
		this.states.put("KS", new LatLon(38.5111, -96.8005));
		this.states.put("KY", new LatLon(37.6690, -84.6514));
		this.states.put("LA", new LatLon(31.1801, -91.8749));
		this.states.put("MA", new LatLon(42.2373, -71.5314));
		this.states.put("MD", new LatLon(39.0724, -76.7902));
		this.states.put("ME", new LatLon(44.6074, -69.3977));
		this.states.put("MI", new LatLon(43.3504, -84.5603));
		this.states.put("MN", new LatLon(45.7326, -93.9196));
		this.states.put("MO", new LatLon(38.4623, -92.3020));
		this.states.put("MP", new LatLon(14.8058, 145.5505));
		this.states.put("MS", new LatLon(32.7673, -89.6812));
		this.states.put("MT", new LatLon(46.9048, -110.3261));
		this.states.put("NC", new LatLon(35.6411, -79.8431));
		this.states.put("ND", new LatLon(47.5362, -99.7930));
		this.states.put("NE", new LatLon(41.1289, -98.2883));
		this.states.put("NH", new LatLon(43.4108, -71.5653));
		this.states.put("NJ", new LatLon(40.3140, -74.5089));
		this.states.put("NM", new LatLon(34.8375, -106.2371));
		this.states.put("NV", new LatLon(38.4199, -117.1219));
		this.states.put("NY", new LatLon(42.1497, -74.9384));
		this.states.put("OH", new LatLon(40.3736, -82.7755));
		this.states.put("OK", new LatLon(35.5376, -96.9247));
		this.states.put("OR", new LatLon(44.5672, -122.1269));
		this.states.put("PA", new LatLon(40.5773, -77.2640));
		this.states.put("PR", new LatLon(18.2766, -66.3350));
		this.states.put("RI", new LatLon(41.6772, -71.5101));
		this.states.put("SC", new LatLon(33.8191, -80.9066));
		this.states.put("SD", new LatLon(44.2853, -99.4632));
		this.states.put("TN", new LatLon(35.7449, -86.7489));
		this.states.put("TX", new LatLon(31.1060, -97.6475));
		this.states.put("UT", new LatLon(40.1135, -111.8535));
		this.states.put("VA", new LatLon(37.7680, -78.2057));
		this.states.put("VI", new LatLon(18.0001, -64.8199));
		this.states.put("VT", new LatLon(44.0407, -72.7093));
		this.states.put("WA", new LatLon(47.3917, -121.5708));
		this.states.put("WI", new LatLon(44.2563, -89.6385));
		this.states.put("WV", new LatLon(38.4680, -80.9696));
		this.states.put("WY", new LatLon(42.7475, -107.2085));
	}
	
	public static void main(String args[]) {
		File f = new File("states.txt");
		try {
			BufferedReader b = new BufferedReader(new FileReader(f));
			String s;
			while((s = b.readLine()) != null) {
				String[] values = s.split(",");
				System.out.println("this.states.put(\"" + values[0] + "\", new LatLon(" + values[1] + ", " + values[2] + "));");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class LatLon {
	double lat;
	double lon;
	
	LatLon(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}
}