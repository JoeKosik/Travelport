package json.problem;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseWrapper {
	
	private RestResponseResultList restResponse = new RestResponseResultList();
	
	public ResponseWrapper() {
		super();
	}

	@JsonGetter("RestResponse")
	public RestResponseResultList getRestResponse() {
		return restResponse;
	}

	@JsonSetter("RestResponse")
	public void setRestResponse(RestResponseResultList restResponse) {
		this.restResponse = restResponse;
	}

	@Override
	public String toString() {
		return "ResponseWrapper [restResponseList=" + restResponse + "]";
	}
	
	

}
