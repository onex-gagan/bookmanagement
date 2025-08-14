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
            return ResponseEntity.badRequest().build();
        }

        // 1. Map request to entity
        UserEntity userEntity = UserMapper.toEntity(userCreateRequest);

        // 2. Save entity
        UserEntity savedUser = userService.saveUser(userEntity);

        // 3. Map saved entity to API model
        User apiUser = UserMapper.toApiUser(savedUser);

        // 4. Return with 201 Created
        return ResponseEntity.status(201).body(apiUser);
    }

    @Override
    public ResponseEntity<User>usersUserIdGet(Integer userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }

        UserEntity userEntity = userService.getUserById(userId);
        if (userEntity == null) {
            return ResponseEntity.notFound().build();
        }

        User apiUser = UserMapper.toApiUser(userEntity);
        return ResponseEntity.ok(apiUser);
    }

    @Override
    public ResponseEntity<Void> usersUserIdDelete(Integer userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }

        UserEntity userEntity = userService.getUserById(userId);
        if (userEntity == null) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<User> usersUserIdPut(Integer userId, UserUpdateRequest userUpdateRequest) {
        if (userId == null || userUpdateRequest == null) {
            return ResponseEntity.badRequest().build();
        }

        // 1. Fetch existing entity
        UserEntity existingUser = userService.getUserById(userId);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        // 2. Update fields from request
        UserMapper.updateEntityFromRequest(existingUser, userUpdateRequest);

        // 3. Save updated entity
        UserEntity updatedUser = userService.saveUser(existingUser);

        // 4. Map to API model
        User apiUser = UserMapper.toApiUser(updatedUser);

        return ResponseEntity.ok(apiUser);
    }


}
