package com.foodordering;

import com.foodordering.entity.User;
import com.foodordering.util.HibernateUtil;
import org.hibernate.Session;

public class App 
{
    public static void main( String[] args )
    {
        Session session = HibernateUtil.getSessionFactory().openSession();

        User user = new User("dharmander","9310266755","dharm123");
        session.beginTransaction();


        session.save(user);

        session.getTransaction().commit();

        System.out.println("User created successfully!");
        System.out.println(user);

    }
}
