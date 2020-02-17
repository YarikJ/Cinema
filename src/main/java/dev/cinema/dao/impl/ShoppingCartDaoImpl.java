package dev.cinema.dao.impl;

import dev.cinema.dao.ShoppingCartDao;
import dev.cinema.lib.Dao;
import dev.cinema.models.ShoppingCart;
import dev.cinema.models.User;
import dev.cinema.util.HibernateUtil;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (Session session  = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long shoppingCartId = (Long) session.save(shoppingCart);
            transaction.commit();
            shoppingCart.setId(shoppingCartId);
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert shopping cart entity!", e);
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            ShoppingCart shoppingCart = session.get(ShoppingCart.class, user.getId());
            Hibernate.initialize(shoppingCart.getTickets());
            return shoppingCart;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving shopping cart of user with id "
                    + user.getId(), e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (Session session  = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't update shopping cart entity with id "
                    + shoppingCart.getId(), e);
        }
    }
}