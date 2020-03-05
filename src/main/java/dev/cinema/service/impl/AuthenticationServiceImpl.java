package dev.cinema.service.impl;

import dev.cinema.dao.RoleDao;
import dev.cinema.models.Role;
import dev.cinema.models.User;
import dev.cinema.service.AuthenticationService;
import dev.cinema.service.ShoppingCartService;
import dev.cinema.service.UserService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final RoleDao roleDao;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService, RoleDao roleDao) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.roleDao = roleDao;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        List<Role> roles = roleDao.getRoles(List.of("USER"));
        user.setRoles(roles);
        User registeredUser = userService.add(user);
        shoppingCartService.registerNewShoppingCart(registeredUser);
        return registeredUser;
    }
}
