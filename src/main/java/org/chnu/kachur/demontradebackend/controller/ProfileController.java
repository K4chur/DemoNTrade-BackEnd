package org.chnu.kachur.demontradebackend.controller;

import lombok.RequiredArgsConstructor;
import org.chnu.kachur.demontradebackend.model.Profile;
import org.chnu.kachur.demontradebackend.model.User;
import org.chnu.kachur.demontradebackend.service.ProfileService;
import org.chnu.kachur.demontradebackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<Profile>> getUserProfiles(Authentication auth) {
        User user = userService.getByUsername(auth.getName());
        return ResponseEntity.ok(profileService.getAllProfiles(user));
    }

    @PostMapping("/add")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile, Authentication auth) {
        User user = userService.getByUsername(auth.getName());
        return ResponseEntity.ok(profileService.addNew(profile, user));
    }
}
