package com.travelport.exercise.hospitality.kosik.joe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
	
	private static final Logger log = LogManager.getLogger(ServletInitializer.class);
	
	public ServletInitializer() {
		super();
		log.debug("[DBG-00] From Constructor");
	}	

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		SpringApplicationBuilder sab = null;
		
		log.debug("[DBG-01.1] PRE application.sources(TravelportApplication.class);");
		sab = application.sources(TravelportApplication.class);
		log.debug("[DBG-01.2] Returning SpringApplicationBuilder");
		
		return sab;
	}

}
