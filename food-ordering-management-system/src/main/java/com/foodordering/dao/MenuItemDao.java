package com.foodordering.dao;

import com.foodordering.entity.MenuItem;
import com.foodordering.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class MenuItemDao {

    public void saveMenuItem(MenuItem menuItem) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(menuItem);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public MenuItem getMenuItem(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(MenuItem.class, id);
        }
    }

    public List<MenuItem> getAllMenuItems() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from MenuItem", MenuItem.class).list();
        }
    }

    public List<MenuItem> getAllMenuItemsByMenuId(int menuId) {
        List<MenuItem> menuItems = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Correct HQL query with parameter binding
            menuItems = session.createQuery("from MenuItem where menu.id = :menuId", MenuItem.class)
                               .setParameter("menuId", menuId)
                               .list();
        } catch (Exception e) {
            e.printStackTrace();  // Log or handle the error as needed
        }
        return menuItems;
    }
    
    public void updateMenuItem(MenuItem menuItem) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(menuItem);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteMenuItem(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            MenuItem menuItem = session.get(MenuItem.class, id);
            if (menuItem != null) {
                session.delete(menuItem);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

}
