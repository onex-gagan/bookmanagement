package org.example.bookmanagement.api;

import lombok.RequiredArgsConstructor;
import org.example.bookmanagement.entity.UserEntity;
import org.example.bookmanagement.mapper.UserMapper;
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
}
