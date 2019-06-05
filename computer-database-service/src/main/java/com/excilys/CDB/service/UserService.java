package com.excilys.CDB.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.CDB.core.model.User;
import com.excilys.CDB.persistence.repository.UserRepository;
import com.sun.java.swing.action.SaveAction;

@Service
    public class UserService implements UserDetailsService {
        private final UserRepository userRepository;
        @Autowired
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Objects.requireNonNull(username);
            
            User user = userRepository.findUserWithName(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return user;
        }
        
        public void SavePass(User user) {
        	userRepository.save(user);
        }
        
        
    }
