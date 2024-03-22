package com.app.todojpa.controllers;


import com.app.todojpa.dtos.AuthenticationRecordDto;
import com.app.todojpa.dtos.RegisterRecordDto;
import com.app.todojpa.models.UserModel;
import com.app.todojpa.services.AuthorizationService;
import com.app.todojpa.services.TokenService;
import com.app.todojpa.utils.ApiGlobalResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<ApiGlobalResponseDto> login(@RequestBody @Valid AuthenticationRecordDto dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new ApiGlobalResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiGlobalResponseDto> register(@RequestBody @Valid RegisterRecordDto dto){
        UserDetails userDetails = authorizationService.findByEmail(dto.email());
        if (userDetails != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        var newUser = authorizationService.save(dto, encryptedPassword);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiGlobalResponseDto(newUser));
    }

}
