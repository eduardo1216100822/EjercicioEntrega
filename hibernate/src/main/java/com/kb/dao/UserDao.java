package com.kb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.kb.model.User;
import com.kb.util.HibernateUtil;

public class UserDao {
	
	//Create of CRUD
	 public void addUser(User user) {
	        Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            trns = session.beginTransaction();
	            session.save(user);
	            session.getTransaction().commit();
	        } catch (RuntimeException e) {
	            if (trns != null) {
	                trns.rollback();
	            }
	            e.printStackTrace();
	        } finally {

	            session.close();
	        }
	    }
	 
	 //Read of CRUD
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
	        List<User> users = new ArrayList<User>();
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            users = session.createQuery("from User").getResultList();
	        } catch (RuntimeException e) {
	            e.printStackTrace();
	        } finally {
	            session.flush();
	            session.close();
	        }
	        return users;
	    }

	//Read of CRUD
	    public User getUserById(int userid) {
	        User user = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            String hqlQuery = "from User where id = :id";
	            @SuppressWarnings("rawtypes")
				Query query = session.createQuery(hqlQuery);
	            query.setParameter("id", userid);
	            user = (User) query.getSingleResult();
	        } catch (RuntimeException e) {
	            e.printStackTrace();
	        } finally {
	            session.flush();
	            session.close();
	        }
	        return user;
	    }
	    
	  //Update of CRUD
	    public void updateUser(User user) {
	        Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            trns = session.beginTransaction();
	            session.update(user);
	            session.getTransaction().commit();
	        } catch (RuntimeException e) {
	            if (trns != null) {
	                trns.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            session.flush();
	            session.close();
	        }
	    }

	    //Delete of CRUD
	    public void deleteUser(int userid) {
	        Transaction trns = null;
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            trns = session.beginTransaction();
	            User user = (User) session.load(User.class, new Integer(userid));
	            session.delete(user);
	            session.getTransaction().commit();
	        } catch (RuntimeException e) {
	            if (trns != null) {
	                trns.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            session.flush();
	            session.close();
	        }
	    }
 
}
