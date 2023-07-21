package com.erkan.springauthservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erkan.springauthservice.entity.User;
import com.erkan.springauthservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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
        User user = userService.findByUsername(username);
        if (user == null) {
            return "cannot find user";
        }
        return user.getUsername();
    }
}
