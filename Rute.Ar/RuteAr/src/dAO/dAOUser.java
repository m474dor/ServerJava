package dAO;

import java.util.List;

import javax.persistence.*;

import iDAO.iDAOUser;
import manager.JPAUtility;
import model.Activity;
import model.Human;
import model.Note;
import model.Rating;
import model.Route;
import model.User;

public class dAOUser implements iDAOUser{

	@Override
	public List<Route> getMyRoutes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Route> getDoneRoutes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rating> getMyRatings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Note> getMyNotes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findId(long id) {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			return em.find(User.class, id);
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
	public List<User> findAll() {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			Query q = em.createQuery("FROM User");
			List<User> resultList = (List<User>)q.getResultList();
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
	public boolean insert(User a) {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			EntityTransaction etx = em.getTransaction();
			etx.begin();
			
			em.persist(a);
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
	public boolean update(User a) {
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
	public boolean delete(User a) {
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

	@Override
	public User findDni(Integer dni) {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			Query q = em.createQuery("SELECT a FROM User a WHERE a.dni= :custName").setParameter("custName", dni.intValue());
			User result =  (User) q.getSingleResult();
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
	public User findUsername(String username) {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			Query q = em.createQuery("SELECT a FROM User a WHERE a.userName= :custName").setParameter("custName", username);
			User result =  (User) q.getSingleResult();
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
	public List<User> findDoneBy(Route aux) {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			Query q = em.createQuery("SELECT u FROM User u WHERE u.done= :custName").setParameter("custName", aux);
			List<User> resultList = (List<User>)q.getResultList();
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

}