package com.erkan.springauthservice.util.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.erkan.springauthservice.dto.UsernameEmailCheckResponseDto;

import jakarta.persistence.Tuple;

@Component
public class TupleBackedMapToUsernameEmailCheckResponseDtoConverter implements Converter<Tuple, UsernameEmailCheckResponseDto>{

    @Override
    public UsernameEmailCheckResponseDto convert(Tuple arg0) {
        Boolean usernameStatus = arg0.get("usernameTaken", Long.class).equals(1L) ? true : false;
        Boolean emailStatus = arg0.get("emailTaken", Long.class).equals(1L) ? true : false;
        return new UsernameEmailCheckResponseDto(usernameStatus, emailStatus);
    }

}
