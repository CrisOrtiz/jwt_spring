package com.tutorial.crud.repository;

import com.tutorial.crud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String nombreUsuario);
    boolean existsByUserName(String nombreUsuario);
    boolean existsByEmail(String email);

}
