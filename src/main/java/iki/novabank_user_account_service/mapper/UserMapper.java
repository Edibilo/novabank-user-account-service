package iki.novabank_user_account_service.mapper;

import iki.novabank_user_account_service.dto.UserDto;
import iki.novabank_user_account_service.entity.User;

public class UserMapper {

    public static User mapToUser(UserDto userDto){
        User user=new User();
        user.setCreatedAt(userDto.getCreatedAt());
        user.setEmail(userDto.getEmail());
        user.setEnabled(userDto.isEnabled());
        user.setFirstName(userDto.getFirstName());
        user.setId(userDto.getId());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static UserDto mapToUserDto(User user){
        UserDto userDto=new UserDto();
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setEmail(user.getEmail());
        userDto.setEnabled(user.isEnabled());
        userDto.setFirstName(user.getFirstName());
        userDto.setId(user.getId());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        userDto.setRoleId(user.getRole().getId());
        return userDto;
    }
}
