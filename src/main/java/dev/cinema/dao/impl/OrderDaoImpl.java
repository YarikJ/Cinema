package dev.cinema.dao.impl;

import dev.cinema.dao.OrderDao;
import dev.cinema.lib.Dao;
import dev.cinema.models.Order;
import dev.cinema.models.User;
import dev.cinema.util.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order completeOrder(Order order) {
        Transaction transaction = null;
        try (Session session  = HibernateUtil.getSessionFactory().openSession()) {
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
        return null;
    }
}
