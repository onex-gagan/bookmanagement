package org.example.bookmanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.bookmanagement.entity.BookEntity;
import org.example.bookmanagement.model.Book;
import org.example.bookmanagement.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }
}
