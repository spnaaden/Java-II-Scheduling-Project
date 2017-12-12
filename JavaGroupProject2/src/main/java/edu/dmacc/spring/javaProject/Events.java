package edu.dmacc.spring.javaProject;

import java.util.ArrayList;
import java.util.List;

public class Events {

	List<Event> allEvents;
	List<Integer> list = new ArrayList<Integer>();

	public List<Event> getAllEvents() {
		return allEvents;
	}

	public void setAllEvents(List<Event> allEvents) {
		this.allEvents = allEvents;
	}
	
	public Event getFirst() {
		return allEvents.get(0);
	}
	
	public void applyUserAndPass(String user, String pass) {
		for (Event e: allEvents) {
			e.setUser(user);
			e.setPass(pass);
		}
	}
	
	
}
