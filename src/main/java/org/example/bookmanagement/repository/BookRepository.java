package org.example.bookmanagement.repository;

import org.example.bookmanagement.entity.BookEntity;
import org.example.bookmanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
