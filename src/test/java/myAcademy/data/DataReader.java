package myAcademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
//	
//	public List<HashMap<String ,String>>getJsonDataToMap() throws IOException{
// //read or convert json to string with java we can do it 
//	String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test/java//myAcademy//data//PurchaseOrder.json"),StandardCharsets.UTF_8);
//	
//	
//	//convert string to hashmap -Jackson Databind
//	//Jackson Databind is dependency which converts string content to hash map
//	ObjectMapper mapper = new ObjectMapper();
//	List<HashMap<String,String>>  data=mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){
//		
//	});
//	return data;
//	
//	
//	//convert string to hashmap
//	
//	
//	}	
}
