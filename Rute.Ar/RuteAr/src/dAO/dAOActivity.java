package dAO;

import java.util.List;
import iDAO.iDAOActivity;
import model.Activity;
import javax.persistence.*;
import manager.JPAUtility;

public class dAOActivity implements iDAOActivity{

	@Override
	public Activity findByName(String name) {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			Query q = em.createQuery("SELECT a FROM Activity a WHERE a.name= :custName").setParameter("custName", name);
			Activity result =  (Activity) q.getSingleResult();
			return result;
		} 
		catch (Exception e){
			return null;
		}
		finally {
			if(em!=null && em.isOpen()){
				em.close();
			}
		}
	}

	@Override
	public Activity findId(long id) {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			return em.find(Activity.class, id);
		} 
		catch (Exception e){
			return null;
		}
		finally {
			if(em!=null && em.isOpen()){
				em.close();
			}
		}
	}

	@Override
	public List<Activity> findAll() {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			Query q = em.createQuery("FROM Activity");
			List<Activity> resultList = (List<Activity>)q.getResultList();
			return resultList;
		} 
		catch (Exception e){
			return null;
		}
		finally {
			if(em!=null && em.isOpen()){
				em.close();
			}
		}
	}

	@Override
	public boolean insert(Activity a) {
		try {
			EntityManager em = JPAUtility.getEntityManager();
			EntityTransaction etx = em.getTransaction();
			etx.begin();
			
			em.persist(a);
			etx.commit();
			em.close();
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Activity a) {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			EntityTransaction etx = em.getTransaction();
			etx.begin();
			em.merge(a);
			etx.commit();
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}

	@Override
	public boolean delete(Activity a) {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			EntityTransaction etx = em.getTransaction();
			etx.begin();
			em.remove(em.contains(a) ? a : em.merge(a));
			etx.commit();
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}

}
