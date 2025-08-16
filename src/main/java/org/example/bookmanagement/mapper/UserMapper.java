package org.example.bookmanagement.mapper;

import org.example.bookmanagement.entity.UserEntity;
import org.example.bookmanagement.model.User;
import org.example.bookmanagement.model.UserUpdateRequest;
import org.example.bookmanagement.model.UserCreateRequest;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    private UserMapper() {
        // Private constructor to prevent instantiation
    }

    public static User toApiUser(UserEntity entity) {
        if (entity == null) return null;

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
        if (request == null) return null;

        return UserEntity.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .email(request.getEmail())
                .build();
    }

    public static void updateEntityFromRequest(UserEntity entity, UserUpdateRequest request) {
        if (request.getName() != null) entity.setName(request.getName());
        if (request.getEmail() != null) entity.setEmail(request.getEmail());
        if (request.getPhone() != null) entity.setPhone(request.getPhone());
        if (request.getAddress() != null) entity.setAddress(request.getAddress());
    }
}
