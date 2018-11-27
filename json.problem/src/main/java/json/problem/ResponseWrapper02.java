package json.problem;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseWrapper02 {
	
	private RestResponseResultItem restResponse = new RestResponseResultItem();

	public ResponseWrapper02() {
		super();
	}

	@JsonGetter("RestResponse")
	public RestResponseResultItem getRestResponse() {
		return restResponse;
	}

	@JsonSetter("RestResponse")
	public void setRestResponse(RestResponseResultItem restResponse) {
		this.restResponse = restResponse;
	}

	@Override
	public String toString() {
		return "ResponseWrapper02 [restResponse=" + restResponse + "]";
	}
	
	

}
