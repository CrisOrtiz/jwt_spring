package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Role;
import com.tutorial.crud.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRolName(RoleName roleName);
}
