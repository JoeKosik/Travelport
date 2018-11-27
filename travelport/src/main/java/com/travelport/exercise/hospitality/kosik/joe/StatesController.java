package com.travelport.exercise.hospitality.kosik.joe;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class StatesController {
	
	private static final Logger log = LogManager.getLogger(StatesController.class);
	
	@Value("${all.states.url.endpoint}") String strAllStatesURLEndpoint;
	
	@Value("${one.state.url.endpoint}") String strOneStateURLEndpoint;
	
	public StatesController() {
		super();
		log.debug("[DBG-00] From Constructor");
	}
	
	/* 
	 * To debug / ensure that Property Values dependency injected 
	 */
	@GetMapping("/states-endpoint")
	public String getStatesEndpoint() {
		log.debug("[DBG-01] Entry");
		String strRetVal = null;
		strRetVal = "[" + this.strAllStatesURLEndpoint + "] [" + this.strOneStateURLEndpoint + "]";
		log.debug("[DBG-01] About to return:[ {} ]", strRetVal);
		return strRetVal;
	}
	
	/*
	 * To debug / confirm suspicion that will need special Jackson Json annotations
	 * in JSON to POJO conversion  
	 */
	@ResponseBody
	@RequestMapping(value = "/debug/states/AL&GA", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<State> getDebugAlabamaAndGeorgia() {
		List<State> lstRetVal = new ArrayList<State>(); 
		
		State state = new State(1, "USA", "Alabama", "AL", "135767SKM", "Birmingham", "Montgomery");
		
		lstRetVal.add(state);
		
		state = new State(10, "USA", "Georgia", "GA", "57513SKM", "Atlanta", "Atlanta");
		lstRetVal.add(state);
		
		log.debug("[DBG-01] About to return:[{}]", lstRetVal);
		
		return lstRetVal;
	}
	
	/*
	 * To debug / confirm that initial REST template call to returning correctly.
	 * Only outputting return JSON string. 
	 * No JSON:POJO conversion yet
	 */
	@GetMapping("/states/1")
	public String getAllStates1() {
		log.debug("[DBG-02] Entry");
		String strRetVal = null;
		
		try {
			URI uri = new URI(this.strAllStatesURLEndpoint);
			log.debug("[DBG-03] URI:[{}]", uri.toString());
			
			RestTemplate restTemplate = new RestTemplate();
			
			log.debug("[DBG-04] PRE restTemplate.getForObject()");
			strRetVal = restTemplate.getForObject(uri, String.class);
			
			log.debug("[DBG-05] POST restTemplate.getForObject()");
			
			//log.debug("");
		} catch (URISyntaxException e) {
			log.error("[ERR-07] ", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
			strRetVal = "error";
		}
		log.debug("[DBG-08] Returning: [{}]", strRetVal);		
		return strRetVal;
	}
	
	/*
	 * To debug / confirm that initial REST template call to returning correctly.
	 * Suspicion confirmed JSON to POJO will encounter complications (UTF-8 conversion issue for Guam's capital / id:52
	 * 
	 * Specifying UTF-8 charset does not make Guam's capital's special characters "go away" 
	 * 
	 * JSON:POJO conversion using Jackson ObjectMapper following separate experimentation
	 * in project:[json.problem] 
	 * 
	 * Encountering Json parsing exception when converting File of JSON into POJO
	 */	
	@GetMapping("/states/2")
	public String getAllStates2() {
		log.debug("[DBG-09] Entry");
		String strRetVal = null;
		ResponseWrapperResultList rw = null;
		
		try {
			URI uri = new URI(this.strAllStatesURLEndpoint);
			log.debug("[DBG-10] URI:[{}]", uri.toString());
			
			RestTemplate restTemplate = new RestTemplate();
			
			log.debug("[DBG-11] PRE restTemplate.getForObject()");
			strRetVal = restTemplate.getForObject(uri, String.class);
			
			log.debug("[DBG-12] POST restTemplate.getForObject()");
			
			log.debug("[DBG-13] strRetVal ==============================");
			log.debug( strRetVal );
			log.debug("[DBG-14] strRetVal ==============================");
			
			byte[] arrbyte = strRetVal.getBytes(Charset.forName("UTF-8")); 
			
			String strJSONVaue = new String(arrbyte, Charset.forName("UTF-8"));
			
			log.debug("[DBG-15] strJSONVaue ==============================");
			log.debug( strJSONVaue );
			log.debug("[DBG-16] strJSONVaue ==============================");
			
			ObjectMapper objmpr = new ObjectMapper();
			rw = objmpr.readValue(strJSONVaue, ResponseWrapperResultList.class);
			
			if (rw == null) {
				log.debug("[DBG-17] RW is NULL");
			}else {
				log.debug("[DBG-18] RW may have been converted");
				log.debug("[DBG-19] " + rw.toString());
				
				List<State> lstState = rw.getRestResponse().getResult();
				
				if (lstState != null) {
					int i=0;
					for (State state: lstState) {
						log.debug("[DBG-19.2][{}] {}", i, state);
						i++;
					}
				}else {
					log.info("[INF-19] lstState is null");
				}
			}
			
			strRetVal = "success?";			
			
			
		} catch (URISyntaxException e) {
			log.error("[ERR-20] ", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
			strRetVal = "error";
		} catch (JsonParseException e) {
			log.error("[ERR-21] ", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			log.error("[ERR-22] ", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			log.error("[ERR-23] ", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("[DBG-24] Returning: [{}]", strRetVal);		
		return strRetVal;
	} 
	
	/*
	 * To debug / confirm that initial REST template call to returning correctly.
	 * Suspicion confirmed JSON to POJO will encounter complications (UTF-8 conversion issue for Guam's capital / id:52
	 * 
	 * Specifying UTF-8 charset does not make Guam's capital's special characters "go away" 
	 * 
	 * JSON:POJO conversion using Jackson ObjectMapper following separate experimentation
	 * in project:[json.problem] 
	 * 
	 * Encountering Json parsing exception when converting File of JSON into POJO
	 * 
	 * **** Discovery: special UTF-8 characters in Guam's capital does NOT trigger exception
	 * 		when called from RestTemplate
	 */		
	@ResponseBody
	@GetMapping("/states/3")
	public List<State> getAllStates3() {
		log.debug("[DBG-25] Entry");
		String strRetVal = null;
		ResponseWrapperResultList rwrl = null;
		List<State> lstStateRetVal = new ArrayList<State>();
		
		try {
			URI uri = new URI(this.strAllStatesURLEndpoint);
			log.debug("[DBG-26] URI:[{}]", uri.toString());
			
			RestTemplate restTemplate = new RestTemplate();
			
			log.debug("[DBG-27] PRE restTemplate.getForObject()");
			strRetVal = restTemplate.getForObject(uri, String.class);
			
			log.debug("[DBG-28] POST restTemplate.getForObject()");
			 
			
			String strJSONVaue = strRetVal;
			
			log.debug("[DBG-29] strJSONVaue ==============================");
			log.debug( strJSONVaue );
			log.debug("[DBG-30] strJSONVaue ==============================");
			
			ObjectMapper objmpr = new ObjectMapper();
			rwrl = objmpr.readValue(strJSONVaue, ResponseWrapperResultList.class);
			
			if (rwrl == null) {
				log.debug("[DBG-31] RWRL is NULL");
			}else {
				log.debug("[DBG-32] RWRL may have been converted");
				log.debug("[DBG-33] " + rwrl.toString());
				
				List<State> lstState = rwrl.getRestResponse().getResult();
				
				if (lstState != null) {
					lstStateRetVal = lstState;
					int i=0;
					for (State state: lstState) {
						log.debug("[DBG-34][{}] {}", i, state);
						i++;
					}
				}else {
					log.info("[INF-35] lstState is null");
				}
			}
			
			strRetVal = "success?";			
			
			
		} catch (URISyntaxException e) {
			log.error("[ERR-36] ", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
			strRetVal = "error";
		} catch (JsonParseException e) {
			log.error("[ERR-37] ", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			log.error("[ERR-38] ", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			log.error("[ERR-39] ", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("[DBG-40] Returning: [{}]", lstStateRetVal);		
		return lstStateRetVal;
	} 
 
	/*
	 * Cleaned up version of code that calls given URL Endpoint to get all US states.
	 * **** The Workhorse ***
	 * 
	 * Confirms that Jackson annotions on POJOs are correct resulting in correct 
	 * marshalling from JSON to POJO
	 */
	@ResponseBody
	@GetMapping("/states/all")
	public List<State> getAllStates4() {
		log.debug("[DBG-41] Entry");
		List<State> lstStateRetVal = new ArrayList<State>();
		ResponseWrapperResultList rwrl = null;
		
		try {
			URI uri = new URI(this.strAllStatesURLEndpoint);
			log.debug("[DBG-42] URI:[{}]", uri.toString());
			
			RestTemplate restTemplate = new RestTemplate();
			
			log.debug("[DBG-43] PRE restTemplate.getForObject()");
			rwrl = restTemplate.getForObject(uri, ResponseWrapperResultList.class);
			
			log.debug("[DBG-44] POST restTemplate.getForObject()");
			
			
			if (rwrl == null) {
				log.debug("[DBG-45] RWRL is NULL");
			}else {
				log.debug("[DBG-46] RWRL may have been converted");
				log.debug("[DBG-47] " + rwrl.toString());
				
				List<State> lstState = rwrl.getRestResponse().getResult();
				
				if (lstState != null) {
					lstStateRetVal = lstState;
					int i=0;
					for (State state: lstState) {
						log.debug("[DBG-48][{}] {}", i, state);
						i++;
					}
				}else {
					log.info("[INF-49] lstState is null");
				}
			}
			
		} catch (URISyntaxException e) {
			log.error("[ERR-50] ", e);
			// TODO Auto-generated catch block
			e.printStackTrace();		
		
		}
		log.debug("[DBG-51] Returning: [{}]", lstStateRetVal);	
		
		return lstStateRetVal;
	}
	
	/*
	 *  (Line Item #4)
	 * Official 1st submission for RESTful webservice endpoint
	 * that gets JSON data from provided URL, goes through the entire list of 55
	 * State POJO to extract out only Alabama and Gerogie
	 * 
	 */
	@ResponseBody
	@GetMapping("/states/1/AL&GA")
	public List<State> getAlabamaAndGeorgia() {
		List<State> lstRetVal = new ArrayList<State>();
		List<State> lstStateAll = null;
		
		Set<String> setstrSearchTarget = new HashSet<String>();
		setstrSearchTarget.add("AL");
		setstrSearchTarget.add("GA");
		
		lstStateAll = this.getAllStates4();
		
		for (State state: lstStateAll) {
			String strAbbreviation = state.getAbbr();
			
			if  ( setstrSearchTarget.contains(strAbbreviation) ) {
				lstRetVal.add(state);
			}
		}		
		return lstRetVal;
	}
	
	/*
	 * Experiment to confirm is possible to invoke given URL endpoint to 
	 * return just 1 state instead of list of 55 states
	 */
	@ResponseBody
	@GetMapping("/state/{stateAbbrev}")
	public State getOneState(@PathVariable String stateAbbrev) {
		State stateRetVal = null;
		
		log.debug("[DBG-52] Entry, stateAbbrev:[{}]", stateAbbrev);
		ResponseWrapperResultItem rwri = null;
		
		try {
			URI uri = new URI(this.strOneStateURLEndpoint + stateAbbrev);
			log.debug("[DBG-53] URI:[{}]", uri.toString());
			
			RestTemplate restTemplate = new RestTemplate();
			
			log.debug("[DBG-54] PRE restTemplate.getForObject()");
			rwri = restTemplate.getForObject(uri, ResponseWrapperResultItem.class);
			
			log.debug("[DBG-55] POST restTemplate.getForObject()");
			
			if (rwri == null) {
				log.warn("[WRN-56] ResponseWrapperResultItem is NULL.");
			}else {
				log.debug("[DBG-57] ResponseWrapperResultItem is an Object: [ {} ]", rwri);
				
				if  ( (rwri.getRestResponse() != null) &&
					  (rwri.getRestResponse().getResult() != null)
					) {
					stateRetVal = rwri.getRestResponse().getResult();
				}
			}
			
		} catch (URISyntaxException e) { 
			log.error("[ERR-58] ", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("[DBG-59] Returning: [{}]",  stateRetVal);
		return stateRetVal;
	}

	/*
	 * Alternative submission for RESTful webservice endpoint (Line Item #4)
	 * that makes 2 outgoing RESTful webservice calls instead of processing
	 * an entire list of 55 results
	 */
	@ResponseBody
	@GetMapping("/states/2/AL&GA")
	public List<State> getAlabamaAndGeorgiaViaIndividualFetch() {
		List<State> lstRetVal = new ArrayList<State>();		
		State state = null;
		
		log.debug("[DBG-60] Entry");
		
		//Fetch Alabama
		state = this.getOneState("AL");
		if (state == null) {
			log.warn("[DBG-61] 'AL' fetch returned NULL");
		} else {
			log.debug("[DBG-62] 'AL' fetch returned: [ {} ]", state);
			lstRetVal.add(state);
		}
		
		//Fetch Georgia
		state = this.getOneState("GA");
		if (state == null) {
			log.warn("[DBG-63] 'GA' fetch returned NULL");
		} else {
			log.debug("[DBG-64] 'GA' fetch returned: [ {} ]", state);
			lstRetVal.add(state);
		}
				
		log.debug("[DBG-65] Returning: [{}]", lstRetVal);
		
		return lstRetVal;
	}

}
