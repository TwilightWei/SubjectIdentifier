package main.java.compare;

import java.util.ArrayList;

import org.json.JSONObject;

public class Compare {
	public ArrayList<String> compareClass(ArrayList<String> classAPIData, JSONObject classAPIUsages) {
		ArrayList<String> matches = new ArrayList<String>();
		for(String classDatum: classAPIData) {
			if(classAPIUsages.has(classDatum)) {
				matches.add(classDatum);
			}
		}
		return matches;
	}
	
	public ArrayList<String> compareMethod(ArrayList<String> methodAPIData, ArrayList<String> methodAPIUsages) {
		ArrayList<String> matches = new ArrayList<String>();
		for(String methodAPIUsage: methodAPIUsages) {
			if(methodAPIData.contains(methodAPIUsage)) {
				//matches.add(methodAPIUsage);
			} else {
				boolean c1 = false;
				boolean c2 = false;
				for(String methodAPIDatum: methodAPIData) {
					if(methodAPIDatum.substring(0, methodAPIDatum.indexOf("(")).equals(methodAPIUsage.substring(0, methodAPIUsage.indexOf("(")))) {
						c1 = true;
						boolean hasPoints = true;
						boolean check = true;
						String usageParas = methodAPIUsage.substring(methodAPIUsage.indexOf("("));
						String dataParas = methodAPIDatum.substring(methodAPIDatum.indexOf("("));
						String[] usageParaList;
						String[] dataParaList;
						usageParas = removeAngleBrackets(usageParas);
						dataParas = removeAngleBrackets(dataParas);
						
						if(dataParas.contains("...")) {
							dataParas = dataParas.replace("...", "");
						} else {
							hasPoints = false;
						}
						
						usageParaList = usageParas.substring(usageParas.indexOf("(")+1, usageParas.indexOf(")")).split(", ");
						dataParaList = dataParas.substring(dataParas.indexOf("(")+1, dataParas.indexOf(")")).split(", ");
						
						if(usageParaList.length >= dataParaList.length) {
							for(int i=0; i<usageParaList.length; i++) {
								if(i < dataParaList.length) {
									if(hasPoints && i == usageParaList.length-1 && usageParaList[i].contains("[]")) {
										usageParaList[i] = usageParaList[i].substring(0, usageParaList[i].indexOf("["));
									}
									if(!dataParaList[i].equals(usageParaList[i])) {
										if(dataParaList[i].contains(".")) {
											if(!dataParaList[i].substring(dataParaList[i].lastIndexOf(".")+1).equals(usageParaList[i])) {
												check = false;
											}
										} else {
											if(!dataParaList[i].equals("K") && !dataParaList[i].equals("V")) {
												check = false;
											}
										}
									}
								} else {
									if(hasPoints) {
										if(!usageParaList[i].equals(usageParaList[dataParaList.length-1])) {
											check = false;
										}
									} else {
										check = false;
									}
								}
							}
						} else {
							check = false;
						}
						if(check) {
							matches.add(methodAPIUsage);
							matches.add(methodAPIDatum);
							c2 = true;
							break;
						}
					}
				}
				if(c1==true && c2==false) {
					//matches.add(methodAPIUsage);
				}
			}
		}
		return matches;
	}
	
	public String removeAngleBrackets(String string) {
		String cleanString = string;
		while(cleanString.contains(">,")) {
			String left =  cleanString.substring(0, cleanString.indexOf(">,"));
			String right = cleanString.substring(cleanString.indexOf(">,")+1);
			left = left.substring(0, left.indexOf("<"));
			cleanString = left+right;
		}
		while(cleanString.contains(">)")) {
			String left =  cleanString.substring(0, cleanString.indexOf(">)"));
			String right = cleanString.substring(cleanString.indexOf(">)")+1);
			left = left.substring(0, left.indexOf("<"));
			cleanString = left+right;
		}
		while(cleanString.contains(">.")) {
			String left =  cleanString.substring(0, cleanString.indexOf(">."));
			String right = cleanString.substring(cleanString.indexOf(">.")+1);
			left = left.substring(0, left.indexOf("<"));
			cleanString = left+right;
		}
		if(cleanString.contains(">") || cleanString.contains("<")) {
			System.out.println(cleanString);
		}
		
		return cleanString;
	}
}
