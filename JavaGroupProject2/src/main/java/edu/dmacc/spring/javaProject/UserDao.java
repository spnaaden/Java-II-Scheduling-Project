package edu.dmacc.spring.javaProject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDao{
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JavaGroupProject2");
	
	public void insertUser(User userToAdd){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(userToAdd);
		em.getTransaction().commit();
		em.close();
		System.out.println("InsertUser");
		
	}
	
	public User selectUser(User user) {
		EntityManager em = emfactory.createEntityManager();
	
		User thisUser = null;
		
		String userNm = user.getUserName();
		
		TypedQuery<User> typeQ = em.createQuery("SELECT u FROM User u WHERE u.userName = :nm",User.class)
				.setParameter("nm", userNm);
		
		try {
		thisUser = typeQ.getResultList().get(0);
		if (thisUser.getUserName().equals(user.getUserName())) {
			if (thisUser.getPassword().equals(user.getPassword())) {
				return thisUser;
			}
		}
		}
			catch (Exception e){
			System.out.println("No user with this username found.");
			thisUser = null;
		}
		
		em.close();
		return thisUser;
	}
}

