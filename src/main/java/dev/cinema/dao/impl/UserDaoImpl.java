package dev.cinema.dao.impl;

import dev.cinema.dao.UserDao;
import dev.cinema.models.Order;
import dev.cinema.models.Role;
import dev.cinema.models.User;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
        Transaction transaction = null;
        try (Session session  = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long userId = (Long) session.save(user);
            transaction.commit();
            user.setId(userId);
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Could not insert user entity!", e);
        }
    }

    @Override
    public User findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
//            Query query = session.createQuery("from User where email = :email");
//            query.setParameter("email", email);
//            return (User) query.getSingleResult();

            return session.createQuery("select user from User user"
                    + " join fetch user.roles where user.email = :email", User.class)
                    .setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving user with email " + email, e);
        }
    }

    @Override
    public User get(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, userId);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving user with id " + userId, e);
        }
    }
}
