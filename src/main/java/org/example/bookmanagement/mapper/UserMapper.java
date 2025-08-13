package org.example.bookmanagement.mapper;

import org.example.bookmanagement.entity.UserEntity;
import org.example.bookmanagement.model.User;

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
}
