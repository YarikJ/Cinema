package dev.cinema.dao.impl;

import dev.cinema.dao.MovieDao;
import dev.cinema.lib.Dao;
import dev.cinema.models.Movie;
import dev.cinema.util.HibernateUtil;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieDaoImpl implements MovieDao {
    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        try (Session session  = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long movieId = (Long) session.save(movie);
            transaction.commit();
            movie.setId(movieId);
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert movie entity!", e);
        }
    }

    @Override
    public List<Movie> getAll() {
        try (Session session  = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Movie> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Movie.class);
            criteriaQuery.from(Movie.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all movies", e);
        }
    }
}
