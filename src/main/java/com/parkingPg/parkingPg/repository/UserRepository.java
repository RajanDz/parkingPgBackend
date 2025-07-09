package com.parkingPg.parkingPg.repository;

import com.parkingPg.parkingPg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Optional<User> findByEmail(String email);
}
