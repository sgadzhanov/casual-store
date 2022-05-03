package com.project.casualstore.service.impl;

import com.project.casualstore.model.entity.UserEntity;
import com.project.casualstore.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CasualtyUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public CasualtyUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found!"));

        List<GrantedAuthority> authorities = userEntity
                .getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getUserRole().name()))
                .collect(Collectors.toList());


        return new CasualtyUser(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );
    }
}