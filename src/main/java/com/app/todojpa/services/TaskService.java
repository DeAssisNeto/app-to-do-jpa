package com.app.todojpa.services;

import com.app.todojpa.dtos.TaskRecordDto;
import com.app.todojpa.models.TaskModel;
import com.app.todojpa.repositories.TaskRepository;
import com.app.todojpa.repositories.UserRepository;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;
    public TaskModel save(TaskRecordDto dto, DecodedJWT token){

        return taskRepository.save(new TaskModel(dto, userRepository.findByEmail(token.getSubject())));
    }
}
