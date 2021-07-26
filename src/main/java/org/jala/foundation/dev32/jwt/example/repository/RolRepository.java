package org.jala.foundation.dev32.jwt.example.repository;

import org.jala.foundation.dev32.jwt.example.entity.Role;
import org.jala.foundation.dev32.jwt.example.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
}
