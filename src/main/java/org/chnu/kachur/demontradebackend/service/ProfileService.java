package org.chnu.kachur.demontradebackend.service;

import lombok.RequiredArgsConstructor;
import org.chnu.kachur.demontradebackend.model.Profile;
import org.chnu.kachur.demontradebackend.model.User;
import org.chnu.kachur.demontradebackend.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public List<Profile> getAllProfiles(User user) {
        return profileRepository.findAllByUser(user).orElseThrow();
    }

    public Profile addNew(Profile profile, User user) {
        profile.setUser(user);
        profile.setCreatedAt(LocalDateTime.now());
        return profileRepository.save(profile);
    }
}
