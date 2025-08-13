package org.example.bookmanagement.api;
import org.example.bookmanagement.entity.BookEntity;
import org.example.bookmanagement.service.BookService;
import org.example.bookmanagement.model.Book;
import org.example.bookmanagement.mapper.BookMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Component
@RequiredArgsConstructor
public class BooksApiDelegateImpl implements BooksApiDelegate {

    private final BookService bookService;

    @Override
    public ResponseEntity<List<Book>> booksGet(){
        List<BookEntity> books = bookService.getAllBooks();
        return ResponseEntity.ok(BookMapper.toApiBookList(books));
    }


}
