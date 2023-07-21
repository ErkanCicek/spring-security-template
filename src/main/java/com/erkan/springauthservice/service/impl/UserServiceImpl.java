package com.erkan.springauthservice.service.impl;

import com.erkan.springauthservice.entity.User;

public interface UserServiceImpl {
    //add crud, or methods here to then implement in the service.    

    User findByUsername(String username);
}
