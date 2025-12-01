package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.boodstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryUserRepository implements UserRepository {
    @Override
    public Optional<User> findByUsername(String username) {
        return DataHolder.users.stream().filter(p->p.getUsername().equals(username)).findFirst();
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return DataHolder.users.stream().filter(p->p.getUsername().equals(username) && p.getPassword().equals(password)).findFirst();

    }

    @Override
    public User save(User user) {
        DataHolder.users.removeIf(u->u.getUsername().equals(user.getUsername()));
        DataHolder.users.add(user);
        return user;
    }

    @Override
    public void deleteByUsername(String username) {
        DataHolder.users.removeIf(u -> u.getUsername().equals(username));
    }
}
