package com.security.security.service;

import com.security.security.entity.User;
import lombok.Data;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class UserDetailsImpl implements UserDetails {

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        user.getRoles().forEach(e->{
            grantedAuthorities.add(new SimpleGrantedAuthority(e));
        });
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    public UserDetailsImpl build(User user){
//        List<GrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//        user.getRoles().forEach(e->{
//            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(e);
//            simpleGrantedAuthorities.add(simpleGrantedAuthority);
//        });
//    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singleton(new SimpleGrantedAuthority("USER"));
//    }
//
//    @Override
//    public String getPassword() {
//        return "";
//    }
//
//    @Override
//    public String getUsername() {
//        return "";
//    }
}
