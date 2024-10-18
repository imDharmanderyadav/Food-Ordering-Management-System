package com.foodordering.dao;

import org.hibernate.Transaction;

import org.hibernate.Session;

// import com.foodordering.entity.Cart;
import com.foodordering.entity.User;
import com.foodordering.util.HibernateUtil;
import org.hibernate.query.Query;

public class UserDao {

    // private Session session;
    private Transaction transaction;

    public void saveUser(User user)
    {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public User getUserById(int id){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        }
    }

    public User getUserByEmailAndPassword(String email, String password){

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            // session.beginTransaction();
            Query<User> query = session.createQuery("FROM User WHERE email = :useremail AND password = :password", User.class);

            query.setParameter("useremail", email);
            // query.setParameter("useremail", email);
            query.setParameter("password", password);
            return query.uniqueResult();

        }
      
    }


    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    
}
