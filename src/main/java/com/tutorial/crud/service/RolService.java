package com.tutorial.crud.service;

import com.tutorial.crud.entity.Role;
import com.tutorial.crud.enums.RoleName;
import com.tutorial.crud.repository.RolRepository;
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
        return rolRepository.findByRolName(roleName);
    }

    public void save(Role role){
        rolRepository.save(role);
    }
}
