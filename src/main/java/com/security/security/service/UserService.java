package com.security.security.service;

import com.security.security.entity.User;
import com.security.security.jwt.JwtService;
import com.security.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public User registerUser(User user) {
        user.setCreatedOn(Instant.now());
        user.setModifiedOn(Instant.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        return userRepository.saveUser(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserByUsernameOrEmail(String username) {
        return userRepository.getUserByUsernameOrEmail(username);
    }


    public String verifyUser(String usernameOrEmail, String password) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usernameOrEmail, password));
        if (authenticate.isAuthenticated()){
            return jwtService.generateToken(usernameOrEmail);
        }
        return "authentication failed";
    }
}
