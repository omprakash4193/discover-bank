package com.discovery.service.service;

import com.discovery.service.entity.UserDetails;
import com.discovery.service.exception.GenericException;
import com.discovery.service.exception.UserAuthenticationException;
import com.discovery.service.exception.UserNotFoundException;
import com.discovery.service.models.UserDto;
import com.discovery.service.repository.UserDetailsRepository;
import com.discovery.service.utils.UserUtils;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Service implementation class.
 *
 * @author omprasa2
 * @since 2023-02-21
 */
@Slf4j
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public UserDto registerUser(UserDto userDto) throws GenericException {
        log.info("registerUser for user details {}", userDto);
        UserDetails userDetails = null;
        if (StringUtils.isNotBlank(userDto.getUsername())) {
            UserDetails userDetailsExist = userDetailsRepository.findUserDetailsByUsername(userDto.getUsername());
            if (Objects.isNull(userDetailsExist)) {
                userDetails = UserUtils.userDtoToEntity(userDto);
                userDetails = userDetailsRepository.save(userDetails);
            } else {
                throw new GenericException("User already exist");
            }
        }
        return UserUtils.userEntityToUserDto(userDetails);
    }

    @Override
    public UserDto getUserDetails(String username) throws UserNotFoundException {
        log.info("getUserDetails for username/email {}", username);
        UserDetails userDetails = userDetailsRepository.findUserDetailsByUsername(username);
        if (Objects.isNull(userDetails)) {
            userDetails = userDetailsRepository.findUserDetailsByEmail(username);
            if (Objects.isNull(userDetails)) {
                throw new UserNotFoundException("User not found", String.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        }
        return UserUtils.userEntityToUserDto(userDetails);
    }

    @Override
    public Map<String, Object> loginUser(UserDto userDto) throws UserNotFoundException, UserAuthenticationException {
        log.info("getUserDetails for username/email {}", userDto.getUsername());
        Map<String, Object> tokenMap = new HashMap<>();
        String encryptedPwd = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(12));
        log.info("Encrypted Password {}", encryptedPwd);
        UserDetails userDetails =
                userDetailsRepository.findUserDetailsByUsername(userDto.getUsername());
        if (Objects.isNull(userDetails)) {
            throw new UserNotFoundException("User not found", String.valueOf(HttpStatus.NOT_FOUND.value()));
        } else {
            if (BCrypt.checkpw(userDto.getPassword(), userDetails.getPassword())) {
                String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, userDto.getPassword())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 5 * 60))
                        .claim("firstName", userDetails.getFirstName())
                        .claim("lastName", userDetails.getLastName())
                        .claim("username", userDto.getUsername())
                        .compact();
                tokenMap.put("token", token);
            } else {
                throw new UserAuthenticationException("Invalid Password", HttpStatus.UNAUTHORIZED.toString());
            }
        }
        return tokenMap;
    }
}
