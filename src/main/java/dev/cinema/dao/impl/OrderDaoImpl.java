package dev.cinema.dao.impl;

import dev.cinema.dao.OrderDao;
import dev.cinema.models.Order;
import dev.cinema.models.User;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order completeOrder(Order order) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long orderId = (Long) session.save(order);
            transaction.commit();
            order.setId(orderId);
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert order entity!", e);
        }
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select distinct orders from Order orders"
                    + " join fetch orders.tickets where orders.user = :user", Order.class)
                    .setParameter("user", user).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all orders", e);
        }
    }
}
