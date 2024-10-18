package com.foodordering.dao;

import com.foodordering.entity.Menu;
import com.foodordering.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class MenuDao {

    public void saveMenu(Menu menu) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(menu);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Menu getMenu(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Menu.class, id);
        }
    }

    public List<Menu> getAllMenus() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Menu", Menu.class).list();
        }
    }

    public void updateMenu(Menu menu) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(menu);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteMenu(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Menu menu = session.get(Menu.class, id);
            if (menu != null) {
                session.delete(menu);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
