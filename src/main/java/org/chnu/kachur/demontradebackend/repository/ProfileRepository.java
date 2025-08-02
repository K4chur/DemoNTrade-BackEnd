package org.chnu.kachur.demontradebackend.repository;

import org.chnu.kachur.demontradebackend.model.Profile;
import org.chnu.kachur.demontradebackend.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {

    Optional<List<Profile>> findAllByUser(User user);

}
