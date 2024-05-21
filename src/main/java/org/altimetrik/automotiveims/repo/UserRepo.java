package org.altimetrik.automotiveims.repo;

import org.altimetrik.automotiveims.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    public Optional<User> findByEmail(String email);
}
