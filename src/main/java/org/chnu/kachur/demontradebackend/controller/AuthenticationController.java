package org.chnu.kachur.demontradebackend.controller;

import lombok.RequiredArgsConstructor;
import org.chnu.kachur.demontradebackend.dto.LoginUserDto;
import org.chnu.kachur.demontradebackend.dto.RefreshTokenDto;
import org.chnu.kachur.demontradebackend.dto.RegisterUserDto;
import org.chnu.kachur.demontradebackend.model.User;
import org.chnu.kachur.demontradebackend.response.LoginResponse;
import org.chnu.kachur.demontradebackend.service.AuthenticationService;
import org.chnu.kachur.demontradebackend.service.JwtService;
import org.chnu.kachur.demontradebackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {

        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).build();

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<LoginResponse> refresh_token(@RequestBody RefreshTokenDto refreshTokenDto) {
        String username = jwtService.extractUsername(refreshTokenDto.getRefreshToken());

        User user = userService.getByUsername(username);

        String jwtToken = jwtService.generateToken(user);

        LoginResponse loginResponse = LoginResponse.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).build();

        return ResponseEntity.ok(loginResponse);
    }

}
