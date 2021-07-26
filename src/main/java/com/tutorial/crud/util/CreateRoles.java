package com.tutorial.crud.util;

import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.enums.RolName;
import com.tutorial.crud.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * Very important: this class should execute once to create roles, once created it should be deleted
 * or commented.
 */

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
         /**Rol rolAdmin = new Rol(RolName.ROLE_ADMIN);
         Rol rolUser = new Rol(RolName.ROLE_USER);
         rolService.save(rolAdmin);
         rolService.save(rolUser);*/
    }
}
