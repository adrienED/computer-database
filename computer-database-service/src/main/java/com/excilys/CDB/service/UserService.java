package com.excilys.CDB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.CDB.core.model.User;
import com.excilys.CDB.persistence.repository.UserRepository;

@Service
    public class UserService implements UserDetailsService {
        private final UserRepository userRepository;
        
        @Autowired
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }


    	@Override
    	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    		User user = userRepository.findUserWithName(name).get();
    		if (user == null)
    			throw new UsernameNotFoundException("[ERROR] User does not exist.");
    		return new UserDetailsModel(user);
    	}
    	
    	
    	 public void SavePass(User user) {
         	userRepository.save(user);
         }

        
    }
