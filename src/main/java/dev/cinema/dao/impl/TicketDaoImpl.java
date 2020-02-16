package dev.cinema.dao.impl;

import dev.cinema.dao.TicketDao;
import dev.cinema.lib.Dao;
import dev.cinema.models.Ticket;
import dev.cinema.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {

    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        try (Session session  = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long movieId = (Long) session.save(ticket);
            transaction.commit();
            ticket.setId(movieId);
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert ticket entity!", e);
        }
    }
}
