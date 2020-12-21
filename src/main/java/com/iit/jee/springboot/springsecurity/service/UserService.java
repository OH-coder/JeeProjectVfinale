package com.iit.jee.springboot.springsecurity.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.iit.jee.springboot.springsecurity.model.User;
import com.iit.jee.springboot.springsecurity.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
