package com.erkan.springauthservice.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erkan.springauthservice.dto.AuthenticationRequest;
import com.erkan.springauthservice.dto.AuthenticationResponse;
import com.erkan.springauthservice.dto.RegisterDto;
import com.erkan.springauthservice.dto.UsernameEmailCheckResponseDto;
import com.erkan.springauthservice.entity.User;
import com.erkan.springauthservice.repo.UserRepo;
import com.erkan.springauthservice.service.impl.AuthenticationServiceImpl;
import com.erkan.springauthservice.util.JwtUtil;
import com.erkan.springauthservice.util.converter.TupleBackedMapToUsernameEmailCheckResponseDtoConverter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationServiceImpl{

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final TupleBackedMapToUsernameEmailCheckResponseDtoConverter converter;
    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.username(),
            request.password()
        )
    );
    var user = repo.findByUsername(request.username())
        .orElseThrow();
    var accessToken = jwtUtil.generateToken(user);
    var refreshToken = jwtUtil.generateRefreshToken(user);
    return new AuthenticationResponse(accessToken, refreshToken);
    }

    @Override
    public Boolean register(RegisterDto userInfo) {
            repo.save(new User(
                null,
                userInfo.username(),
                userInfo.email(),
                passwordEncoder.encode(userInfo.password()),
                "USER")                );
        return true; // we are returning true, as yes we registered
    }

    @Override
    public UsernameEmailCheckResponseDto checkUsernameEmailAvailability(String username, String email) {
        return converter.convert(repo.isUsernameAndEmailTaken(username, email));
    }  
}
