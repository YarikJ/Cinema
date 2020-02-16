package dev.cinema.dao.impl;

import dev.cinema.dao.TicketDao;
import dev.cinema.lib.Dao;
import dev.cinema.models.Ticket;
import dev.cinema.util.HibernateUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {
    private static final Logger LOGGER = LogManager.getLogger(TicketDaoImpl.class);

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
            LOGGER.error("Can't insert ticket entity!", e);
            throw new RuntimeException(e);
        }
    }
}
