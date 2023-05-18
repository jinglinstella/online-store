package com.example.demo.service.impl;


import com.example.demo.domain.Owner;
import com.example.demo.repository.OwnerRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Owner owner = ownerRepository.findByEmail(email);
        if (owner == null) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        return new User(owner.getEmail(), owner.getPassword(), new ArrayList<>());
    }
}