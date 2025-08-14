package org.example.bookmanagement.delegate;
import org.example.bookmanagement.entity.BookEntity;
import org.example.bookmanagement.model.BookCreateRequest;
import org.example.bookmanagement.model.BookUpdateRequest;
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

    @Override
    public ResponseEntity<Book>booksBookIdGet(Integer bookId) {
        BookEntity bookEntity = bookService.getBookById(bookId);
        if (bookEntity != null) {
            return ResponseEntity.ok(BookMapper.toApiBook(bookEntity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Book> booksPost(BookCreateRequest bookCreateRequest) {
        BookEntity bookEntity = BookMapper.toEntity(bookCreateRequest);

        BookEntity savedBook = bookService.saveBook(
                bookEntity,
                bookCreateRequest.getUserId() != null ? bookCreateRequest.getUserId().longValue() : null,
                bookCreateRequest.getPriceId() != null ? bookCreateRequest.getPriceId().longValue() : null
        );

        return ResponseEntity.status(201).body(BookMapper.toApiBook(savedBook));
    }

    @Override
    public ResponseEntity<Void> booksBookIdDelete(Integer bookId) {
        boolean deleted = bookService.deleteBook(bookId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Book> booksBookIdPut(Integer bookId, BookUpdateRequest bookUpdateRequest) {
        BookEntity bookEntity = bookService.getBookById(bookId);

        if (bookEntity == null) {
            return ResponseEntity.notFound().build();
        }

        BookEntity updatedBook = bookService.updateBook(bookEntity, bookUpdateRequest);

        return ResponseEntity.ok(BookMapper.toApiBook(updatedBook));
    }

}
