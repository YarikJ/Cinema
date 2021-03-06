package dev.cinema.dao.impl;

import dev.cinema.dao.ShoppingCartDao;
import dev.cinema.models.ShoppingCart;
import dev.cinema.models.User;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private final SessionFactory sessionFactory;

    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
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
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select shoppingCart"
                    + " from ShoppingCart shoppingCart left join fetch shoppingCart.tickets "
                    + "where shoppingCart.id = :id", ShoppingCart.class)
                    .setParameter("id", user.getId()).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving shopping cart of user with id "
                    + user.getId(), e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
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

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.setTickets(new ArrayList<>());
        update(shoppingCart);
    }
}
