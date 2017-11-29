package view;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.User;

public class StartProgram {

	public static void main(String[] args) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("LoginPage");
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		User toAdd = new User("bchiles", "password", "Brady", "Chiles");
		em.persist(toAdd);
		em.getTransaction().commit();
		
		em.close();
		emfactory.close();

	}

}
