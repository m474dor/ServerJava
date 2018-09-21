package dAO;

import java.util.List;

import javax.persistence.*;

import iDAO.iDAORoute;
import manager.JPAUtility;
import model.Activity;
import model.Difficulty;
import model.MapPoint;
import model.Note;
import model.Photo;
import model.Rating;
import model.Route;
import model.User;

public class dAORoute implements iDAORoute{

	@Override
	public List<Route> getRoutesByDifficulty(Difficulty difficulty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getDoneBy() {
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
	public List<MapPoint> getMyPoints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Photo> getMyPhotos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Route findId(long id) {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			return em.find(Route.class, id);
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
	public List<Route> findAll() {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			Query q = em.createQuery("SELECT r FROM Route r WHERE r.isPublic=1");
			List<Route> resultList = (List<Route>)q.getResultList();
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
	public boolean insert(Route a) {
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
	public boolean update(Route a) {
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
	public boolean delete(Route a) {
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
	public List<Route> findByUser(User id) {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			Query q = em.createQuery("SELECT r FROM Route r WHERE r.owner= :custName").setParameter("custName", id);
			List<Route> resultList = (List<Route>)q.getResultList();
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
	public List<Route> findByActivity(Activity act) {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			Query q = em.createQuery("SELECT r FROM Route r WHERE r.activity= :custName").setParameter("custName", act);
			List<Route> resultList = (List<Route>)q.getResultList();
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
	public Route findInserted(User id,String name,Activity act) {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			Query q = em.createQuery("SELECT r FROM Route r WHERE r.owner= :custOwner AND r.name= :custName AND r.activity= :custAct").setParameter("custOwner", id).setParameter("custName", name).setParameter("custAct", act);
			Route resultList = (Route)q.getSingleResult();
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