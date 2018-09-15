package dAO;

import java.util.List;

import javax.persistence.*;

import iDAO.iDAODifficulty;
import manager.JPAUtility;
import model.Difficulty;

public class dAODifficulty implements iDAODifficulty{

	@Override
	public Difficulty findId(long id) {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			return em.find(Difficulty.class, id);
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
	public List<Difficulty> findAll() {
		EntityManager em = null;
		try {
			em = JPAUtility.getEntityManager();
			Query q = em.createQuery("FROM Difficulty");
			List<Difficulty> resultList = (List<Difficulty>)q.getResultList();
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
	public boolean insert(Difficulty a) {
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
	public boolean update(Difficulty a) {
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
	public boolean delete(Difficulty a) {
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