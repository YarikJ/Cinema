package dev.cinema.service.impl;

import dev.cinema.exceptions.AuthenticationException;
import dev.cinema.models.User;
import dev.cinema.service.AuthenticationService;
import dev.cinema.service.ShoppingCartService;
import dev.cinema.service.UserService;
import dev.cinema.util.HashUtil;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

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
        User registeredUser = userService.add(user);
        shoppingCartService.registerNewShoppingCart(registeredUser);
        return registeredUser;
    }
}
