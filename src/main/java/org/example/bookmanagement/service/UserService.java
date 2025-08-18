package org.example.bookmanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.bookmanagement.entity.UserEntity;
import org.example.bookmanagement.exception.ResourceNotFoundException;
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

    public List<UserEntity> getAllUsersWithBooks() {
        return userRepository.findAll();
    }

    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity getUserById(Integer userId) {
        return userRepository.findById(userId.longValue())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    public void deleteUserById(Integer userId) {
        UserEntity userEntity = getUserById(userId);
        userRepository.delete(userEntity);
    }
}
