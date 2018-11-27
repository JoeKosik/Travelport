package json.problem;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Application {

	public Application() {
		// TODO Auto-generated constructor stub
	}
	
	public static void writeout1() {
		State state = new State(1, "USA", "Alabama", "AL", "135767SKM", "Birmingham", "Montgomery");
		
		RestResponseResultList rr = new RestResponseResultList();
		rr.getMessages().add("Message Goes Here 11:01");
		rr.getResult().add(state);
		
		ResponseWrapper rw = new ResponseWrapper();
		rw.setRestResponse(rr);
		
		System.out.println("[DBG-01] About to writeout: " + rw.toString() + " as JSON");
		
		ObjectMapper objmpr = new ObjectMapper();				
		
		try {
			String strJSON = objmpr.writeValueAsString(rw);
			
			
			System.out.println("As JSON: " + strJSON);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void attemptAllStatesRead() {
		//File file = new File("C:\\Users\\kosijoe\\Documents\\workspace-sts-3.9.5.RELEASE\\allStates.json");
		//File file = new File("C:\\Users\\kosijoe\\Documents\\workspace-sts-3.9.5.RELEASE\\allStates02.json");
		File file = new File("C:\\Users\\kosijoe\\Documents\\workspace-sts-3.9.5.RELEASE\\allStates_UTF8.json");
		
		System.out.println("[DBG-02] Attempting read JSON file:[" + file.getAbsolutePath() + "] and convert to object ");
		ObjectMapper objmpr = new ObjectMapper();
		
		/*
		//JSON from file to Object
		User user = mapper.readValue(new File("c:\\user.json"), User.class);
		 */
		
		ResponseWrapper rwReadAttempt = null;
		
		try {
			rwReadAttempt = objmpr.readValue(file, ResponseWrapper.class);
			
			System.out.println("[DBG-03 Successful read ?]");
		} catch (JsonParseException e) {			
			System.out.println("[ERR-04]");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.println("[ERR-05]");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("[ERR-06]");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (rwReadAttempt == null) {
			System.out.println("[ERR-07] POST JSON conversion resulted in NULL");
		} else {
			System.out.println("[DBG-08] Not Null");
			System.out.println("Object .... " + rwReadAttempt.toString());
		}
	}
	
	public static void attemptOneStateRead() {
		
		File file = new File("C:\\Users\\kosijoe\\Documents\\workspace-sts-3.9.5.RELEASE\\stateCO.json");
		
		System.out.println("[DBG-09] Attempting read JSON file:[" + file.getAbsolutePath() + "] and convert to object ");
		ObjectMapper objmpr = new ObjectMapper();
			
		
		ResponseWrapper02 rw2ReadAttempt = null;
		
		try {
			rw2ReadAttempt = objmpr.readValue(file, ResponseWrapper02.class);
			
			System.out.println("[DBG-10] Successful read ?");
		} catch (JsonParseException e) {			
			System.out.println("[ERR-11]");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.println("[ERR-12]");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("[ERR-13]");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (rw2ReadAttempt == null) {
			System.out.println("[ERR-14] POST JSON conversion resulted in NULL");
		} else {
			System.out.println("[DBG-15] Not Null");
			System.out.println("Object .... " + rw2ReadAttempt.toString());
		}
		
	}

	public static void main(String[] args) {
		System.out.println("\n PROGRAM START \n");
		//writeout1();
		//attemptAllStatesRead();
		attemptOneStateRead();
	}

}
