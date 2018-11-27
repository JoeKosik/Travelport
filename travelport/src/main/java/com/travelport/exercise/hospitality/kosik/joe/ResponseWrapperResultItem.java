package com.travelport.exercise.hospitality.kosik.joe;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseWrapperResultItem {
	
	private RestResponseResultItem restResponseResultItem = new RestResponseResultItem();

	public ResponseWrapperResultItem() {
		super();
	}

	@JsonGetter("RestResponse")
	public RestResponseResultItem getRestResponse() {
		return restResponseResultItem;
	}

	@JsonSetter("RestResponse")
	public void setRestResponse(RestResponseResultItem restResponse) {
		this.restResponseResultItem = restResponse;
	}

	@Override
	public String toString() {
		return "ResponseWrapperResultItem [restResponseResultItem=" + restResponseResultItem + "]";
	}


}
