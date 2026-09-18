package iki.fordow.mapper;

import iki.fordow.dto.UserDto;
import iki.fordow.entity.User;

public class UserMapper {
    public static User mapToUser(UserDto userDto){
        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setStatus(userDto.getStatus());
        return user;
    }

    public static UserDto mapToUserDto(User user){
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        userDto.setRoleId(user.getRole().getId());
        userDto.setStatus(user.getStatus());
        if(user.getRole().getId()!=null){
            userDto.setRoleName(user.getRole().getName());
        }
        return userDto;
    }
}
