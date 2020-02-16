package dev.cinema.dao.impl;

import dev.cinema.dao.UserDao;
import dev.cinema.lib.Dao;
import dev.cinema.models.User;
import dev.cinema.util.HibernateUtil;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class UserDaoImpl implements UserDao {

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
            throw new RuntimeException("Can't insert movie entity!", e);
        }
    }

    @Override
    public User findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User where email = :email");
            query.setParameter("email", email);
            return (User) query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving user with email " + email, e);
        }
    }
}
