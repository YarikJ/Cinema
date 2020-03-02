package dev.cinema.controllers;

import dev.cinema.models.User;
import dev.cinema.models.dto.UserResponseDto;
import dev.cinema.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/email")
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        return convertToDto(userService.findByEmail(email));
    }

    private UserResponseDto convertToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
