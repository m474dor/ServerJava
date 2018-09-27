package dAO;

import java.util.List;

import javax.persistence.*;

import iDAO.iDAOPhoto;
import manager.JPAUtility;
import model.Photo;
import model.Route;

public class dAOPhoto implements iDAOPhoto{

	@Override
	public Photo findId(long id) {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			return em.find(Photo.class, id);
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
	public List<Photo> findAll(Route id) {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			Query q = em.createQuery("SELECT p FROM Photo p WHERE p.route= :custId").setParameter("custId", id);
			List<Photo> resultList = (List<Photo>)q.getResultList();
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
	public boolean insert(Photo a) {
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
	public boolean update(Photo a) {
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
	public boolean delete(Photo a) {
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