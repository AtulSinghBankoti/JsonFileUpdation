package com;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

 
  public class JsonFileUpdationDemo {
  
	  
	  public static void main(String[] args) { 
		  //JSON parser object to parse read file
	        JSONParser jsonParser = new JSONParser();
	        List<JSONObject> l;
	        JSONArray res = new JSONArray();
	        try (FileReader reader = new FileReader("sample.json"))
	        {
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	 
	            JSONArray employeeList = (JSONArray) obj;
	            //System.out.println(employeeList.size());
	             
	            
	             l=(List<JSONObject>) employeeList.stream().map(ob->{
	            	JSONObject jsonObject= (JSONObject)ob;
	            	JSONObject student = (JSONObject)jsonObject.get("Student");
	            	if(student.get("id").equals("300") ) {
	            		student.put("id", "100");
	            	}
	            	return jsonObject;
	            }).collect(Collectors.toList());
	             
	             res.addAll(l);
	             
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
  
	        //System.out.println("res:>"+res.toJSONString());
	        
	        
	        try (FileWriter file = new FileWriter("sample.json"))
	        {
	        	 file.write(res.toJSONString());
	             file.flush(); 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	       System.out.println("file has been updated...");
	  }
  }