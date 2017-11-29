package edu.dmacc.spring.schedulingproject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EventDao {
	
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("SchedulingProject");
	
	public void insertEvent(Event eventToAdd){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(eventToAdd);
		em.getTransaction().commit();
		em.close();
		//emfactory.close();	
	}


}
