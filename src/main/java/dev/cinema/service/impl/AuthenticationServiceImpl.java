package dev.cinema.service.impl;

import dev.cinema.exceptions.AuthenticationException;
import dev.cinema.lib.Inject;
import dev.cinema.lib.Service;
import dev.cinema.models.User;
import dev.cinema.service.AuthenticationService;
import dev.cinema.service.UserService;
import dev.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (!user.getPassword().equals(HashUtil.hashPassword(password, user.getSalt()))) {
            throw new AuthenticationException();
        }
        return user;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        byte[] salt = HashUtil.getSalt();
        user.setSalt(salt);
        user.setPassword(HashUtil.hashPassword(password, salt));
        return userService.add(user);
    }
}
