package json.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestResponseResultList {
	
	private List<String> messages = new ArrayList<String>();
	
	private List<State> result = new ArrayList<State>();
	
	public RestResponseResultList() {
		super();
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public List<State> getResult() {
		return result;
	}

	public void setResult(List<State> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "RestResponse [messages=" + messages + ", result=" + result + "]";
	}
}
