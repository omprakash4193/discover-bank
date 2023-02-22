package com.discovery.service.utils;

import com.discovery.service.entity.UserDetails;
import com.discovery.service.models.UserDto;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;

/**
 * Util class to do transformation.
 *
 * @author omprasa2
 * @since 2023-02-21
 */
public class UserUtils {

    public static UserDetails userDtoToEntity(UserDto userDto){
        UserDetails userDetails = new UserDetails();
        BeanUtils.copyProperties(userDto, userDetails);
        userDetails.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(12)));
        return userDetails;
    }

    public static UserDto userEntityToUserDto(UserDetails userDetails){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);
        userDto.setUserId(userDetails.getId());
        return userDto;
    }
}
