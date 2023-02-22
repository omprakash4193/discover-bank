package com.discovery.service.models;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class.
 *
 * @author omprasa2
 * @since 2023-02-21
 */
@Data
@NoArgsConstructor
public class UserDto {
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
