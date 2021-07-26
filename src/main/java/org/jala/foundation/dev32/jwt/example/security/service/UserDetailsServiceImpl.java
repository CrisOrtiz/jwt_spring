package org.jala.foundation.dev32.jwt.example.security.service;

import org.jala.foundation.dev32.jwt.example.entity.User;
import org.jala.foundation.dev32.jwt.example.security.entities.UserPrincipal;
import org.jala.foundation.dev32.jwt.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getByUserName(userName).get();
        return UserPrincipal.build(user);
    }
}
