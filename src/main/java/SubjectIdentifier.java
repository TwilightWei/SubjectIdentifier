package main.java;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.json.JSONObject;

import main.java.compare.Compare;
import main.java.config.ConfigReader;
import main.java.file.FileIO;

public class SubjectIdentifier {
	public static void main(String[] args) {
		ConfigReader configReader = new ConfigReader();
		Compare comparer = new Compare();
		FileIO fileIO;
		ArrayList<String> classAPIData;
		ArrayList<String> methodAPIData;
		ArrayList<String> fieldAPIData;
		ArrayList<String> depClassAPIData;
		ArrayList<String> depMethodAPIData;
		ArrayList<String> depFieldAPIData;
		
		JSONObject classAPIUsages;
		ArrayList<String> methodAPIUsages;
		JSONObject fieldAPIUsages;
		
		ArrayList<String> classResults;
		ArrayList<String> depClassResults;
		
		ArrayList<String> methodResults;
		ArrayList<String> depMethodResults;
		
		ArrayList<String> fieldResults;
		ArrayList<String> depFieldResults;
		
		String apiusage = new String();
		String apidata = new String();
		
		final String configPath = "D:\\Users\\user\\git\\SubjectIdentifier\\src\\config.properties";
		
		apidata = configReader.readConfig(configPath, "apidata");
		apiusage = configReader.readConfig(configPath, "apiusage");
		fileIO = new FileIO();
		
		classAPIData = fileIO.readList(apidata + "\\APIData\\Class");
		classAPIUsages = fileIO.readJson(apiusage + "\\APIUsage\\Class");
		methodAPIData = fileIO.readList(apidata + "\\APIData\\Method");
		methodAPIUsages = fileIO.readList(apiusage + "\\APIUsage\\MethodList");
		fieldAPIData = fileIO.readList(apidata + "\\APIData\\Field");
		fieldAPIUsages = fileIO.readJson(apiusage + "\\APIUsage\\Field");
		
		depClassAPIData = fileIO.readList(apidata + "\\APIData\\DeprecatedClass");
		depMethodAPIData = fileIO.readList(apidata + "\\APIData\\DeprecatedMethod");
		depFieldAPIData = fileIO.readList(apidata + "\\APIData\\DeprecatedField");
		
		classResults = comparer.compareClass(classAPIData, classAPIUsages);
		depClassResults = comparer.compareClass(depClassAPIData, classAPIUsages);
		
		methodResults = comparer.compareMethod(methodAPIData, methodAPIUsages);
		depMethodResults = comparer.compareMethod(depMethodAPIData, methodAPIUsages);
		
		fieldResults = comparer.compareField(fieldAPIData, fieldAPIUsages);
		depFieldResults = comparer.compareField(depFieldAPIData, fieldAPIUsages);
		
		fileIO.clearFolder(apiusage + "\\Result");
		for(String classResult : classResults){
			fileIO.writeString(apiusage + "\\Result\\Class", classResult);
		}
		for(String depClassResult : depClassResults){
			fileIO.writeString(apiusage + "\\Result\\DeprecatedClass", depClassResult);
		}
		for(String methodResult : methodResults){
			fileIO.writeString(apiusage + "\\Result\\Method", methodResult);
		}
		for(String depMethodResult : depMethodResults){
			fileIO.writeString(apiusage + "\\Result\\DeprecatedMethod", depMethodResult);
		}
		for(String fieldResult : fieldResults){
			fileIO.writeString(apiusage + "\\Result\\Field", fieldResult);
		}
		for(String depFieldResult : depFieldResults){
			fileIO.writeString(apiusage + "\\Result\\DeprecatedField", depFieldResult);
		}
		System.out.println("Finished");
	}
}
