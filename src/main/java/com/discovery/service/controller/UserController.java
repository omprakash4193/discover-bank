package com.discovery.service.controller;

import com.discovery.service.exception.GenericException;
import com.discovery.service.exception.UserAuthenticationException;
import com.discovery.service.exception.UserNotFoundException;
import com.discovery.service.models.UserDto;
import com.discovery.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * This is controller class to define the api's.
 *
 * @author omprasa2
 * @since 2023-02-21
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDto registerUser(@RequestBody UserDto userDto) throws GenericException {
        return userService.registerUser(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserDto userDto) throws UserNotFoundException, UserAuthenticationException {
        return new ResponseEntity<>(userService.loginUser(userDto), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public UserDto getUsername(@PathVariable("username") String username) throws UserNotFoundException {
        return userService.getUserDetails(username);
    }
}
