package dev.cinema.service.impl;

import dev.cinema.dao.UserDao;
import dev.cinema.lib.Inject;
import dev.cinema.lib.Service;
import dev.cinema.models.User;
import dev.cinema.service.ShoppingCartService;
import dev.cinema.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
