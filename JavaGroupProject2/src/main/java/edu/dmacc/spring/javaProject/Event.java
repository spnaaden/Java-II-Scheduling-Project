package edu.dmacc.spring.javaProject;



import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

@Entity
@Table(name="events")
public class Event {

	public int getEvent_ID() {
		
		return event_ID;
	}

	public void setEvent_ID(int event_ID) {
		
		this.event_ID = event_ID;
	}

	public int getUser_ID() {
	
		return user_ID;
	}

	public void setUser_ID(int user_ID) {
	
		this.user_ID = user_ID;
	}

	public String getEventName() {
		
		return eventName;
	}

	public void setEventName(String eventName) {
		
		this.eventName = eventName;
	}

	public String getEventDescription() {
		
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		
		this.eventDescription = eventDescription;
	}

	public String getEventDate() {
		
		return eventDate;
	}

	public String getEventDatefrmt() {
		
		String tmpString = "";
		
		// 
		return tmpString;
	}
	public void setEventDate(String eventDate) {
		
		this.eventDate = eventDate;
	}

	public String getEventStartTime() {
		
		return eventStartTime;
	}

	public void setEventStartTime(String eventStartTime) {

		this.eventStartTime = eventStartTime;
	}

	public String getEventEndTime() {
		
		return eventEndTime;
	}

	public void setEventEndTime(String eventEndTime) {
		
		this.eventEndTime = eventEndTime;
	}

	public String getEventAddress() {
		
		return eventAddress;
	}

	public void setEventAddress(String eventAddress) {
		
		this.eventAddress = eventAddress;
	}
	
	public boolean isValid() {
		boolean valid = true;
		if (eventName.equals("") || eventName.equals(null)) {
			eventName = "Please fill!";
			valid = false;
		}
		
		if (eventDescription.equals("") || eventDescription.equals(null)) {
			eventDescription = "Please fill!";
			valid = false;
		}
		
		if (eventDate.equals("") || eventDate.equals(null)) {
			eventDate = "Please fill!";
			valid = false;
		}
		
		if (eventStartTime.equals("") || eventStartTime.equals(null)) {
			eventStartTime = "Please fill!";
			valid = false;
		}
		
		if (eventEndTime.equals("") || eventEndTime.equals(null)) {
			eventEndTime = "Please fill!";
			valid = false;
		}
		if (eventAddress.equals("") || eventAddress.equals(null)) {
			eventAddress = "Please fill!";
			valid = false;
		}
		
		
		
		return valid; 
		
		
	}

	
	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSaved() {
		return saved;
	}

	public void setSaved(String saved) {
		this.saved = saved;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int event_ID;
	
	private String pass;
	private String user;
	private int user_ID;
	private String eventName;
	private String eventDescription;
	private String eventDate;
	private String eventStartTime;
	private String eventEndTime;
	private String eventAddress;
	private String saved;
	
	
	
	
}
