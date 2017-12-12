package edu.dmacc.spring.javaProject;

import java.awt.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import edu.dmacc.spring.javaProject.*;

import edu.dmacc.spring.javaProject.User;

public class EventDao {
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JavaGroupProject2");
	
	public void insertEvent(Event eventToAdd){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(eventToAdd);
		em.getTransaction().commit();
		
		//emfactory.close();	
	}
	
	public Events selectEvents(int userId) {
		EntityManager em = emfactory.createEntityManager();
		
		System.out.println("Inside select events");
		System.out.println("userId: " + userId);
		Events events = new Events(); // An object to store all the events in 
		
		TypedQuery<Event> typeQ = em.createQuery("SELECT e FROM Event e WHERE e.user_ID = :id",Event.class)
				.setParameter("id", userId); // pull all events under this userid.
		
		events.setAllEvents(typeQ.getResultList()); // Store the list inside the events object.
		
		return events; // pass back the object containing the list of events.
		
	}
	
	public void delete(int eventId) {
		EntityManager em = emfactory.createEntityManager();
		System.out.println("Inside delete event");
		System.out.println("event id: " + eventId);
		
		Event event = em.find(Event.class, eventId);
		 
		  em.getTransaction().begin();
		  em.remove(event);
		  em.getTransaction().commit();
		  em.close();
	}
	
	public void updateEvents(Event event) {
		EntityManager em = emfactory.createEntityManager();
		System.out.println("Inside Update Event");
		System.out.println("Event Stuff: " + event.getEventAddress() + ", Event ID: " + event.getEvent_ID());
		
		em.getTransaction().begin();
		Event eventToUpdate = em.find(Event.class,event.getEvent_ID());
		eventToUpdate.setUser_ID(event.getUser_ID());
		eventToUpdate.setEventAddress(event.getEventAddress());
		eventToUpdate.setEventDate(event.getEventDate());
		eventToUpdate.setEventDescription(event.getEventDescription());
		eventToUpdate.setEventEndTime(event.getEventEndTime());
		eventToUpdate.setEventName(event.getEventName());
		eventToUpdate.setEventStartTime(event.getEventStartTime());
		eventToUpdate.setUser(event.getUser());
		eventToUpdate.setPass(event.getPass());
		eventToUpdate.setSaved(event.getSaved());
		em.getTransaction().commit();
		em.close();
	}
	
	// Insert event (Adding and deleting events should be handled on page leaving... We should simulate them being gone using javascript on the events page.)
	// Delete event
}
