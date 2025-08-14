package org.example.bookmanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.bookmanagement.entity.UserEntity;
import org.example.bookmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity getUserById(Integer userId) {
        return userRepository.findById(userId.longValue()).orElse(null);
    }

    public void deleteUserById(Integer userId) {
        UserEntity userEntity = getUserById(userId);
        if (userEntity != null) {
            userRepository.delete(userEntity);
        }
    }

}
