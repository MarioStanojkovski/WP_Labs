package mk.ukim.finki.wp.lab.repository.mock;

import mk.ukim.finki.wp.lab.model.User;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    User save(User user);

    void deleteByUsername(String username);
}

