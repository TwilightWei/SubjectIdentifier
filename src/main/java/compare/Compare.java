package main.java.compare;

import java.util.ArrayList;

import org.json.JSONObject;

public class Compare {
	public ArrayList<String> usedAPI(ArrayList<String> classAPIData, JSONObject classAPIUsage) {
		ArrayList<String> overlaps = new ArrayList<String>();
		for(String classDatum: classAPIData) {
			if(classAPIUsage.has(classDatum)) {
				overlaps.add(classDatum);
				System.out.println(classDatum);
			}
		}
		return overlaps;
	}
}
