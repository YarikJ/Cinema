package dev.cinema.dao.impl;

import dev.cinema.dao.RoleDao;
import dev.cinema.models.Role;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Role> getRoles(List<String> roleNames) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select role from Role role "
                    + "where role.roleName in (:roleNames)", Role.class)
                    .setParameterList("roleNames", roleNames).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving roles with role names " + roleNames, e);
        }
    }
}
