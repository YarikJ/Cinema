package dev.cinema.dao.impl;

import dev.cinema.dao.ShoppingCartDao;
import dev.cinema.lib.Dao;
import dev.cinema.models.ShoppingCart;
import dev.cinema.models.User;
import dev.cinema.util.HibernateUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private static final Logger LOGGER = LogManager.getLogger(ShoppingCartDaoImpl.class);

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
            LOGGER.error("Can't insert shopping cart entity!", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(ShoppingCart.class, user.getId());
        } catch (Exception e) {
            LOGGER.error("Error retrieving shopping cart of user with id "
                    + user.getId(), e);
            throw new RuntimeException(e);
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
            LOGGER.error("Can't update shopping cart entity with id "
                    + shoppingCart.getId(), e);
            throw new RuntimeException(e);
        }
    }
}
