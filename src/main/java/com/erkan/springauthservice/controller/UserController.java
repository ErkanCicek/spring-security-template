package com.erkan.springauthservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erkan.springauthservice.dto.AuthenticationRequest;
import com.erkan.springauthservice.dto.AuthenticationResponse;
import com.erkan.springauthservice.dto.RegisterDto;
import com.erkan.springauthservice.dto.UsernameEmailCheckResponseDto;
import com.erkan.springauthservice.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("HELLO");
    }


    @PostMapping("/signup")
    public ResponseEntity<UsernameEmailCheckResponseDto> signup(@RequestBody RegisterDto userInfo){
        //1 check if username and email is ok to use aka not taken
        //2 register user or send error if abovce not false

        UsernameEmailCheckResponseDto availability = 
            authenticationService.checkUsernameEmailAvailability(userInfo.username(), userInfo.email());

        if(availability.usernameTaken() || availability.emailTaken()){
            return ResponseEntity.status(409).body(availability);
        }
        else{
            authenticationService.register(userInfo);
            return ResponseEntity.ok(null);
        }
    }   
}
