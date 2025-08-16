package org.example.bookmanagement.delegate;

import lombok.RequiredArgsConstructor;
import org.example.bookmanagement.entity.UserEntity;
import org.example.bookmanagement.mapper.UserMapper;
import org.example.bookmanagement.model.UserCreateRequest;
import org.example.bookmanagement.model.UserUpdateRequest;
import org.example.bookmanagement.service.UserService;
import org.example.bookmanagement.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsersApiDelegateImpl implements UsersApiDelegate {

    private final UserService userService;

    @Override
    public ResponseEntity<List<User>> usersGet() {
        List<UserEntity> users = userService.getAllUsers();
        return ResponseEntity.ok(UserMapper.toApiUserList(users));
    }

    @Override
    public ResponseEntity<User> usersPost(UserCreateRequest userCreateRequest) {
        if (userCreateRequest == null) {
            throw new IllegalArgumentException("UserCreateRequest cannot be null");
        }

        UserEntity userEntity = UserMapper.toEntity(userCreateRequest);
        UserEntity savedUser = userService.saveUser(userEntity);

        return ResponseEntity.status(201).body(UserMapper.toApiUser(savedUser));
    }

    @Override
    public ResponseEntity<User> usersUserIdGet(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }

        UserEntity userEntity = userService.getUserById(userId);
        return ResponseEntity.ok(UserMapper.toApiUser(userEntity));
    }

    @Override
    public ResponseEntity<Void> usersUserIdDelete(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }

        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<User> usersUserIdPut(Integer userId, UserUpdateRequest userUpdateRequest) {
        if (userId == null || userUpdateRequest == null) {
            throw new IllegalArgumentException("UserId or UserUpdateRequest cannot be null");
        }

        UserEntity existingUser = userService.getUserById(userId);
        UserMapper.updateEntityFromRequest(existingUser, userUpdateRequest);
        UserEntity updatedUser = userService.saveUser(existingUser);

        return ResponseEntity.ok(UserMapper.toApiUser(updatedUser));
    }
}
