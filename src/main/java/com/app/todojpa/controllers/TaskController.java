package com.app.todojpa.controllers;

import com.app.todojpa.dtos.TaskRecordDto;
import com.app.todojpa.services.TaskService;
import com.app.todojpa.services.TokenService;
import com.app.todojpa.utils.ApiGlobalResponseDto;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        var listTask = taskService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(listTask));
    }

}
