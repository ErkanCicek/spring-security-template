package com.erkan.springauthservice.service.impl;

import com.erkan.springauthservice.dto.AuthenticationRequest;
import com.erkan.springauthservice.dto.AuthenticationResponse;
import com.erkan.springauthservice.dto.RegisterDto;
import com.erkan.springauthservice.dto.UsernameEmailCheckResponseDto;

public interface AuthenticationServiceImpl {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    Boolean register(RegisterDto userInfo);
    UsernameEmailCheckResponseDto checkUsernameEmailAvailability(String username, String email);
}
