package com.discovery.service.repository;

import com.discovery.service.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class to connect with db.
 *
 * @author omprasa2
 * @since 2023-02-21
 */
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findUserDetailsByUsername(String username);

    UserDetails findUserDetailsByUsernameAndPassword(String username, String password);

    UserDetails findUserDetailsByEmail(String email);
}
