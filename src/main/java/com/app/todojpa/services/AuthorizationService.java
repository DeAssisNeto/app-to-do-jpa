package com.app.todojpa.services;

import com.app.todojpa.dtos.RegisterRecordDto;
import com.app.todojpa.models.UserModel;
import com.app.todojpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    public UserDetails save(RegisterRecordDto dto, String password){
        return userRepository.save(new UserModel(dto, password));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public UserDetails findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
