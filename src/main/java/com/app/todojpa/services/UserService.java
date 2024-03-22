package com.app.todojpa.services;

import com.app.todojpa.dtos.RegisterRecordDto;
import com.app.todojpa.models.UserModel;
import com.app.todojpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    UserModel saveUser(RegisterRecordDto dto){
        return userRepository.save(new UserModel(dto));
    }
}
