package com.app.todojpa.controllers;

import com.app.todojpa.dtos.TaskRecordDto;
import com.app.todojpa.services.TaskService;
import com.app.todojpa.services.TokenService;
import com.app.todojpa.utils.ApiGlobalResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;
    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<ApiGlobalResponseDto> saveTask(@RequestBody @Valid TaskRecordDto dto,
                                                         @RequestHeader(name="Authorization") String token){
        var newTask = taskService.save(dto, tokenService.decodeToken(token));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiGlobalResponseDto(newTask));
    }

    @GetMapping
    public ResponseEntity<ApiGlobalResponseDto> getAllTask(){
        var listTask = taskService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(listTask));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> getOneTask(@PathVariable(value = "id") UUID id){
        var task = taskService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(task));
    }

}
