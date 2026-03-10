package com.ramondev.desafio_bossabox.controller;


import com.ramondev.desafio_bossabox.dtos.Request.LoginRequest;
import com.ramondev.desafio_bossabox.dtos.Request.UserRequest;
import com.ramondev.desafio_bossabox.dtos.Response.UserResponse;
import com.ramondev.desafio_bossabox.entity.User;
import com.ramondev.desafio_bossabox.mapper.UserMapper;
import com.ramondev.desafio_bossabox.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthControler {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser( @RequestBody UserRequest userRequest){
        User user = UserMapper.toConvertUser(userRequest);
        User newUser = userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toConvertReponse(newUser));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findList(){
        List<User> users = userService.findList();
        List<UserResponse> newList = users.stream().map(user -> UserMapper.toConvertReponse(user)).toList();

        return ResponseEntity.ok().body(newList);
    }


}
