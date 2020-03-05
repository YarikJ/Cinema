package dev.cinema.security;

import dev.cinema.models.Role;
import dev.cinema.models.User;
import dev.cinema.service.UserService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    public CustomUserDetailsService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userService.findByEmail(userEmail);
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(userEmail);
            builder.password(user.getPassword());
            builder.roles(user.getRoles().stream().map(Role::getRoleName).toArray(String[]::new));
        } else {
            throw new UsernameNotFoundException("User not found!");
        }

        return builder.build();
    }
}
