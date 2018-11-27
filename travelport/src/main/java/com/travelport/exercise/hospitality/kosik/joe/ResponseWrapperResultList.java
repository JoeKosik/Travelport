package com.travelport.exercise.hospitality.kosik.joe;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseWrapperResultList {
	
	private RestResponseResultList restResponseResultList = new RestResponseResultList();
	
	public ResponseWrapperResultList() {
		super();
	}

	@JsonGetter("RestResponse")
	public RestResponseResultList getRestResponse() {
		return restResponseResultList;
	}

	@JsonSetter("RestResponse")
	public void setRestResponse(RestResponseResultList restResponse) {
		this.restResponseResultList = restResponse;
	}

	@Override
	public String toString() {
		return "ResponseWrapper [restResponseResultList=" + restResponseResultList.toString() + "]";
	}

}
