package org.jala.foundation.dev32.jwt.example.service;

import org.jala.foundation.dev32.jwt.example.entity.Role;
import org.jala.foundation.dev32.jwt.example.enums.RoleName;
import org.jala.foundation.dev32.jwt.example.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Role> getByRolName(RoleName roleName){
        return rolRepository.findByRoleName(roleName);
    }

    public void save(Role role){
        rolRepository.save(role);
    }
}
