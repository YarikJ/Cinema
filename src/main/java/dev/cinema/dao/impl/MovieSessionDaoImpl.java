package dev.cinema.dao.impl;

import dev.cinema.dao.MovieSessionDao;
import dev.cinema.lib.Dao;
import dev.cinema.models.MovieSession;
import dev.cinema.util.HibernateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    private static final Logger LOGGER = LogManager.getLogger(MovieSessionDaoImpl.class);

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> criteriaQuery = criteriaBuilder
                    .createQuery(MovieSession.class);
            Root<MovieSession> movieSessionRoot = criteriaQuery.from(MovieSession.class);
            Predicate[] predicates = new Predicate[2];
            predicates[0] = criteriaBuilder.equal(movieSessionRoot.get("id"), movieId);
            predicates[1] = criteriaBuilder.between(movieSessionRoot.get("showTime"),
                    LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MAX));
            criteriaQuery.select(movieSessionRoot).where(predicates);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            LOGGER.error("Error retrieving all available movie sessions for" + date, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long movieSessionId = (Long) session.save(movieSession);
            transaction.commit();
            movieSession.setId(movieSessionId);
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Can't insert movie session entity!", e);
            throw new RuntimeException(e);
        }
    }
}
