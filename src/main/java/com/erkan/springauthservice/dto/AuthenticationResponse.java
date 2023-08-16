package com.erkan.springauthservice.dto;

public record AuthenticationResponse(String accessToken, String refreshToken) {
    
}
