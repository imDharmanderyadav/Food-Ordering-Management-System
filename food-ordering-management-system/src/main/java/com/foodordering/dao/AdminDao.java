package com.foodordering.dao;

import org.hibernate.Transaction;

import org.hibernate.Session;
import com.foodordering.entity.Admin;
import com.foodordering.util.HibernateUtil;
import org.hibernate.query.Query;

public class AdminDao {
    
  // private Session session;
  private Transaction transaction;

  public void saveAdmin(Admin admin)
  {
      try(Session session = HibernateUtil.getSessionFactory().openSession()){
          
          transaction = session.beginTransaction();
          session.save(admin);
          transaction.commit();
          session.close();
      }
      catch(Exception e){
          e.printStackTrace();
      }
  }

  public Admin getAdminByUserNameAndPassword(String userName, String password){

      try(Session session = HibernateUtil.getSessionFactory().openSession()){

          // session.beginTransaction();
          Query<Admin> query = session.createQuery("FROM Admin WHERE userName = :username AND password = :password", Admin.class);

          query.setParameter("username", userName);
          // query.setParameter("useremail", email);
          query.setParameter("password", password);
          return query.uniqueResult();

      }
    
  }


  
}

