package com.ramondev.desafio_bossabox.mapper;


import com.ramondev.desafio_bossabox.dtos.Request.UserRequest;
import com.ramondev.desafio_bossabox.dtos.Response.UserResponse;
import com.ramondev.desafio_bossabox.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public User toConvertUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .build();
    }

    public UserResponse toConvertReponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
