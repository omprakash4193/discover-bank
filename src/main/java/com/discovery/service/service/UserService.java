package com.discovery.service.service;

import com.discovery.service.exception.GenericException;
import com.discovery.service.exception.UserAuthenticationException;
import com.discovery.service.exception.UserNotFoundException;
import com.discovery.service.models.UserDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {
    UserDto registerUser(UserDto userDto) throws GenericException;
    UserDto getUserDetails(String username) throws UserNotFoundException;
    Map<String, Object> loginUser(UserDto userDto) throws UserNotFoundException, UserAuthenticationException;
}
