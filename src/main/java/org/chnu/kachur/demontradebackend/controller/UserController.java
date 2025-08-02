package org.chnu.kachur.demontradebackend.controller;

import lombok.RequiredArgsConstructor;
import org.chnu.kachur.demontradebackend.model.User;
import org.chnu.kachur.demontradebackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication auth) {
        User user = userService.getByUsername(auth.getName());
        return ResponseEntity.ok(user);
    }
}
