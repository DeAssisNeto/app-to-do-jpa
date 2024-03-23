package com.app.todojpa.controllers;

import com.app.todojpa.dtos.TaskRecordDto;
import com.app.todojpa.roles.TaskRole;
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
    public ResponseEntity<ApiGlobalResponseDto> getIcompleteTasks(@RequestHeader(name = "Authorization") String token,
                                                                  @RequestParam(required = false) TaskRole search){
        var listTask = taskService.findByCompleted(tokenService.decodeToken(token), search);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(listTask));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> getOneTask(@PathVariable(value = "id") UUID id){
        var task = taskService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> updateTask(@PathVariable(value = "id") UUID id,
                                                           @RequestBody @Valid TaskRecordDto dto){
        var updatedTask = taskService.updateTask(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiGlobalResponseDto> deleteTask(@PathVariable(value = "id") UUID id){
        taskService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
