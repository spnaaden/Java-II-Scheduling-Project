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
	
	// Insert event (Adding and deleting events should be handled on page leaving... We should simulate them being gone using javascript on the events page.)
	// Delete event
}
