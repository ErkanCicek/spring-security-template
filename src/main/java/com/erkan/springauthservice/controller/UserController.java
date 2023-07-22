package com.erkan.springauthservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erkan.springauthservice.dto.AuthenticationRequest;
import com.erkan.springauthservice.dto.AuthenticationResponse;
import com.erkan.springauthservice.repo.UserRepo;
import com.erkan.springauthservice.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserRepo repository;
    private final JwtUtil jwtUtil;

    //testing
    @GetMapping("/user/hi")
    public String hiUser(){
        return "hiUser";
    }

    @GetMapping("/admin/hi")
    public String hiAdmin(){
        return "hiAdmin";
    }

    @GetMapping("/")
    public String hiGuest(@RequestParam String username){
       return username;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticate(request));
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.username(),
            request.password()
        )
    );
    var user = repository.findByUsername(request.username())
        .orElseThrow();
    var jwtToken = jwtUtil.generateToken(user);
    return new AuthenticationResponse(jwtToken);
  }
}
