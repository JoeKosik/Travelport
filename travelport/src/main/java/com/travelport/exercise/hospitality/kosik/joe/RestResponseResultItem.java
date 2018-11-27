package com.travelport.exercise.hospitality.kosik.joe;

import java.util.ArrayList;
import java.util.List;

public class RestResponseResultItem {
	private List<String> messages = new ArrayList<String>();
	
	private State result = new State();

	public RestResponseResultItem() {
		super();
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public State getResult() {
		return result;
	}

	public void setResult(State result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "RestResponseResultItem [messages=" + messages + ", result=" + result + "]";
	}

}
