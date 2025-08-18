package org.example.bookmanagement.mapper;

import org.example.bookmanagement.entity.UserEntity;
import org.example.bookmanagement.model.*;

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
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setAddress(request.getAddress());
    }

    public static UserWithBorrowedBooks toApiUserWithBooks(UserEntity entity) {
        if (entity == null) return null;

        UserWithBorrowedBooks apiUser = new UserWithBorrowedBooks();
        apiUser.setUserId(entity.getId() != null ? entity.getId().intValue() : null);
        apiUser.setName(entity.getName());
        apiUser.setEmail(entity.getEmail());
        apiUser.setPhone(entity.getPhone());
        apiUser.setAddress(entity.getAddress());

        if (entity.getBorrowedBooks() != null) {
            apiUser.setBorrowedBooks(
                    entity.getBorrowedBooks().stream()
                            .map(book -> {
                                Book apiBook = new Book();
                                apiBook.setBookId(book.getId() != null ? book.getId().intValue() : null);
                                apiBook.setBookName(book.getTitle());
                                apiBook.setAuthor(book.getAuthor());

                                if (book.getPrice() != null) {
                                    apiBook.setPrice(book.getPrice().getPrice().floatValue());
                                }
                                return apiBook;
                            })
                            .collect(Collectors.toList())
            );

        }

        return apiUser;
    }

    public static List<UserWithBorrowedBooks> toApiUserWithBooksList(List<UserEntity> entities) {
        return entities.stream()
                .map(UserMapper::toApiUserWithBooks)
                .collect(Collectors.toList());
    }


}
