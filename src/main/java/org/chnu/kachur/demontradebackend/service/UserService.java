package org.chnu.kachur.demontradebackend.service;

import lombok.RequiredArgsConstructor;
import org.chnu.kachur.demontradebackend.model.User;
import org.chnu.kachur.demontradebackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }
}
