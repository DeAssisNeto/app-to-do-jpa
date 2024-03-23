package com.app.todojpa.services;

import com.app.todojpa.dtos.TaskRecordDto;
import com.app.todojpa.models.TaskModel;
import com.app.todojpa.repositories.TaskRepository;
import com.app.todojpa.repositories.UserRepository;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public TaskModel save(TaskRecordDto dto, DecodedJWT token){
        return taskRepository.save(new TaskModel(dto, userRepository.findByEmail(token.getSubject())));
    }

    public List<TaskModel> findAll(){
        return taskRepository.findAll();
    }

    public TaskModel findById(UUID id){
        Optional<TaskModel> task = taskRepository.findById(id);
        if (task.isEmpty()){
            return null;
       }
        return task.get();
    }

    public TaskModel updateTask(UUID id, TaskRecordDto dto){
        var task = taskRepository.findById(id);
        if (task.isEmpty()){
            return null;
        }
        BeanUtils.copyProperties(dto, task.get());
        return taskRepository.save(task.get());
    }

    public void deleteById(UUID id){
        taskRepository.deleteById(id);
    }
}
