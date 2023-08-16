package com.erkan.springauthservice.dto;

public record UsernameEmailCheckResponseDto(Boolean usernameTaken, Boolean emailTaken) {
}