package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class JsonUtils {
	
public String getValueByKey(String responseBody, String keyWord) throws ParseException {
	JSONParser paser= new JSONParser();
	JSONObject jsonObj= (JSONObject) paser.parse(responseBody);
	String value= jsonObj.get(keyWord).toString();
	return value;
}
public String readJsonFile(String filePath) throws IOException, ParseException {
	File jsonFile = new File (filePath);
	FileReader reader= new FileReader(jsonFile);
	JSONParser parser = new JSONParser();
	JSONObject jsonObj =(JSONObject)parser.parse(reader);
	String jsonBody=jsonObj.toJSONString();
return jsonBody;
}

public void copyJsonFile(File rootFile, File destinationFile) throws IOException {
	if(destinationFile.exists()) {
		destinationFile.delete();
	}
	Files.copy(rootFile.toPath(), destinationFile.toPath());
}

public String changeFieldValueByFieldName(File file, String fieldName1, String value1) throws IOException, ParseException {
	String filePath=file.getAbsolutePath();
	String originalFile = new String (Files.readAllBytes(Paths.get(filePath)));
	
	JSONParser parser = new JSONParser();
	JSONObject jsonObj= (JSONObject) parser.parse(originalFile);
	
	if (value1.equals("missing")){
		jsonObj.remove(fieldName1);
		
	}
	
	else if (value1.equals("null")) {
		jsonObj.put(fieldName1, null);
	}
	
	else if(value1.equals("true")) {
		jsonObj.put(fieldName1, true);}
		
	else if (value1.equals("\"\"" )) {
		jsonObj.put(fieldName1, "");
	}
	
	else {
			jsonObj.put(fieldName1, value1);}
	String resultFile=jsonObj.toJSONString();	
	return resultFile;
}

public String checkErrorMessage(String responseBody) {
	String responseBodyCheck="";
	  for (int i =0; i<responseBody.length();i++) {
		  char character=responseBody.charAt(i);
		  if(character!='"') {
			  responseBodyCheck+=character;
		  }
	  }
	  return responseBodyCheck;		
}

}



