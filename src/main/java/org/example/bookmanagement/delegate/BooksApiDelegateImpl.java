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
    public ResponseEntity<List<Book>> booksGet() {
        return ResponseEntity.ok(
                BookMapper.toApiBookList(bookService.getAllBooks())
        );
    }

    @Override
    public ResponseEntity<Book> booksBookIdGet(Integer bookId) {
        BookEntity bookEntity = bookService.getBookById(bookId.longValue());
        return ResponseEntity.ok(BookMapper.toApiBook(bookEntity));
    }

    @Override
    public ResponseEntity<Book> booksPost(BookCreateRequest request) {
        BookEntity bookEntity = BookMapper.toEntity(request);
        BookEntity savedBook = bookService.saveBook(
                bookEntity,
                request.getUserId() != null ? request.getUserId().longValue() : null,
                request.getPriceId() != null ? request.getPriceId().longValue() : null
        );
        return ResponseEntity.status(201).body(BookMapper.toApiBook(savedBook));
    }

    @Override
    public ResponseEntity<Void> booksBookIdDelete(Integer bookId) {
        bookService.deleteBook(bookId.longValue());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Book> booksBookIdPut(Integer bookId, BookUpdateRequest request) {
        BookEntity updatedBook = bookService.updateBook(bookId.longValue(), request);
        return ResponseEntity.ok(BookMapper.toApiBook(updatedBook));
    }
}
