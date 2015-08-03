package com.systran.parser;

import java.util.ArrayList;
import java.util.List;

public class JsonStringParser {
	public List<String> JsonStringifyParser(String[] jsonString){
		List<String> string_list=new ArrayList<String>();
		
		for(int i=0;i<jsonString.length;i++)
		{
			System.out.println(jsonString[i]);
			if(jsonString[i].contains("[") && jsonString[i].contains("]"))
			{
				String parsedString=jsonString[i].substring(jsonString[i].indexOf("[")+1, jsonString[i].indexOf("]"));
				string_list.add(parsedString);
			}
			else if(jsonString[i].contains("\"") && jsonString[i].contains("\""))
			{
				String parsedString=jsonString[i].substring(jsonString[i].indexOf("\"")+1, jsonString[i].indexOf("\"",jsonString[i].indexOf("\"")+1));
				string_list.add(parsedString);
			}
		}
		
		return string_list;
	}
}
