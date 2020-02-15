package dev.cinema.dao.impl;

import dev.cinema.dao.UserDao;
import dev.cinema.lib.Dao;
import dev.cinema.models.User;
import dev.cinema.util.HibernateUtil;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(MovieDaoImpl.class);

    @Override
    public User add(User user) {
        Transaction transaction = null;
        try (Session session  = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long userId = (Long) session.save(user);
            transaction.commit();
            user.setId(userId);
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Can't insert movie entity!", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User where email = :email");
            query.setParameter("email", email);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            LOGGER.error("Error retrieving user with email " + email, e);
            throw new RuntimeException(e);
        }
    }
}
