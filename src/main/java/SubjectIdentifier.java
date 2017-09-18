package main.java;

import java.util.ArrayList;

import org.json.JSONObject;

import main.java.compare.Compare;
import main.java.config.ConfigReader;
import main.java.file.FileIO;

public class SubjectIdentifier {
	public static void main(String[] args) {
		ConfigReader configReader = new ConfigReader();
		Compare compare = new Compare();
		FileIO fileIO;
		ArrayList<String> classAPIData;
		ArrayList<String> depClassAPIData;
		JSONObject classAPIUsage;
		ArrayList<String> classOverlaps;
		ArrayList<String> depClassOverlaps;
		String apiusage = new String();
		String apidata = new String();
		
		final String configPath = "D:\\Users\\user\\git\\SubjectIdentifier\\src\\config.properties";
		
		apidata = configReader.readConfig(configPath, "apidata");
		apiusage = configReader.readConfig(configPath, "apiusage");
		fileIO = new FileIO(apiusage);
		
		classAPIData = fileIO.readList(apidata + "\\APIData\\Class");
		classAPIUsage = fileIO.readJson(apiusage + "\\APIUsage\\Class");
		
		depClassAPIData = fileIO.readList(apidata + "\\APIData\\DeprecatedClass");
		
		classOverlaps = compare.usedAPI(classAPIData, classAPIUsage);
		depClassOverlaps = compare.usedAPI(depClassAPIData, classAPIUsage);
		
		
	}
}
