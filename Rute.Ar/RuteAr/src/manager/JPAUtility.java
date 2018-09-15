package manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class JPAUtility {
	private static EntityManagerFactory emf;
    private static EntityManager entityManager;
	public static EntityManager getEntityManager(){
		if (emf == null){      
        	emf = Persistence.createEntityManagerFactory("miUP");
        }
        entityManager = emf.createEntityManager();    
        return entityManager;
	}
	public static void close(){
		emf.close();
	}
} 