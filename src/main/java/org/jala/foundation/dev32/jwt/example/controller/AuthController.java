package org.jala.foundation.dev32.jwt.example.controller;

import org.jala.foundation.dev32.jwt.example.dto.Message;
import org.jala.foundation.dev32.jwt.example.security.responses.JwtResponse;
import org.jala.foundation.dev32.jwt.example.requests.LoginUser;
import org.jala.foundation.dev32.jwt.example.requests.NewUser;
import org.jala.foundation.dev32.jwt.example.entity.Role;
import org.jala.foundation.dev32.jwt.example.entity.User;
import org.jala.foundation.dev32.jwt.example.enums.RoleName;
import org.jala.foundation.dev32.jwt.example.security.jwt.JwtProvider;
import org.jala.foundation.dev32.jwt.example.service.RolService;
import org.jala.foundation.dev32.jwt.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth/")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody NewUser userName,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Message("Wrong fields or invalid email"),
                HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByUserName(userName.getUserName())) {
            return new ResponseEntity(new Message("Name already exists"), HttpStatus.BAD_REQUEST);
        }
        if (userService.existsByEmail(userName.getEmail())) {
            return new ResponseEntity(new Message("Email already exists"), HttpStatus.BAD_REQUEST);
        }
        User user =
            new User(userName.getName(), userName.getUserName(), userName.getEmail(),
                passwordEncoder.encode(userName.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(rolService.getByRolName(RoleName.ROLE_USER).get());
        if (userName.getRoles().contains("admin")) {
            roles.add(rolService.getByRolName(RoleName.ROLE_ADMIN).get());
        }
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity(new Message("Saved user"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginUser loginUser,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Message("wrong fields"), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication =
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUserName(),
                    loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUsername(),
            userDetails.getAuthorities());
        return new ResponseEntity(jwtResponse, HttpStatus.OK);
    }
}
