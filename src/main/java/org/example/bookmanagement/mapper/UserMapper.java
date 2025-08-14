package org.example.bookmanagement.mapper;

import org.example.bookmanagement.entity.UserEntity;
import org.example.bookmanagement.model.User;
import org.example.bookmanagement.model.UserUpdateRequest;
import org.example.bookmanagement.model.UserCreateRequest;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toApiUser(UserEntity entity) {
        User apiUser = new User();

        apiUser.setUserId(entity.getId() != null ? entity.getId().intValue() : null);
        apiUser.setName(entity.getName());
        apiUser.setPhone(entity.getPhone());
        apiUser.setAddress(entity.getAddress());
        apiUser.setEmail(entity.getEmail());

        // Skipping borrowedBooks for simplicity
        return apiUser;
    }

    public static List<User> toApiUserList(List<UserEntity> entities) {
        return entities.stream()
                .map(UserMapper::toApiUser)
                .collect(Collectors.toList());
    }

    public static UserEntity toEntity(UserCreateRequest request) {
        if (request == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setName(request.getName());
        entity.setPhone(request.getPhone());
        entity.setAddress(request.getAddress());
        entity.setEmail(request.getEmail());

        // Skipping borrowedBooks for simplicity
        return entity;
    }

    public static void updateEntityFromRequest(UserEntity entity, UserUpdateRequest request) {
        if (request.getName() != null) entity.setName(request.getName());
        if (request.getEmail() != null) entity.setEmail(request.getEmail());
        if (request.getPhone() != null) entity.setPhone(request.getPhone());
        if (request.getAddress() != null) entity.setAddress(request.getAddress());
    }



}
