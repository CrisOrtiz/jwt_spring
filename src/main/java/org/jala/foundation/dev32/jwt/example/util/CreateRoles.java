package org.jala.foundation.dev32.jwt.example.util;

import org.jala.foundation.dev32.jwt.example.entity.Role;
import org.jala.foundation.dev32.jwt.example.enums.RoleName;
import org.jala.foundation.dev32.jwt.example.service.RolService;
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
         /*Role rolAdmin = new Role(RoleName.ROLE_ADMIN);
         Role rolUser = new Role(RoleName.ROLE_USER);
         rolService.save(rolAdmin);
         rolService.save(rolUser);*/
    }
}
