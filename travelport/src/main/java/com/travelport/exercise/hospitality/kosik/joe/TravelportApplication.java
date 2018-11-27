package com.travelport.exercise.hospitality.kosik.joe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TravelportApplication {
	
	private static final Logger log = LogManager.getLogger(TravelportApplication.class);
	
	public TravelportApplication() {
		super();
		log.debug("[DBG-00] TravelportApplication: From Constructor");
		//log.debug("[]");
	}

	public static void main(String[] args) {
		log.debug("[MAIN-00] PRE SpringApplication.run()");
		SpringApplication.run(TravelportApplication.class, args);
		log.debug("[MAIN-01] POST SpringApplication.run()");
	}
}
