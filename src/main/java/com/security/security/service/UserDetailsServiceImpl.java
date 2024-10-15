package com.security.security.service;

import com.security.security.entity.User;
import com.security.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        user = userRepository.getUserByUsernameOrEmail(username);
        if (user==null) {
            throw new UsernameNotFoundException("username not found with username or email: " + username);
        }
        return new UserDetailsImpl(user) {
        };
    }
}
