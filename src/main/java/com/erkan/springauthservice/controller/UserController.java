package com.erkan.springauthservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
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
    public String hiGuest(){
        return "hiGuest";
    }
}
