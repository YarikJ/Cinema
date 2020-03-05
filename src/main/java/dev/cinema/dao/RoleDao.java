package dev.cinema.dao;

import dev.cinema.models.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getRoles(List<String> roleNames);
}
