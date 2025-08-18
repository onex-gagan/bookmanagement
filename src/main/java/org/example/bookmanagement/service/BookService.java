package org.example.bookmanagement.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.bookmanagement.entity.BookEntity;
import org.example.bookmanagement.entity.UserEntity;
import org.example.bookmanagement.entity.PriceEntity;
import org.example.bookmanagement.model.BookUpdateRequest;
import org.example.bookmanagement.repository.BookRepository;
import org.example.bookmanagement.repository.UserRepository;
import org.example.bookmanagement.repository.PriceRepository;
import org.springframework.stereotype.Service;
import org.example.bookmanagement.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PriceRepository priceRepository;
    private final UserService userService;
    private final PriceService priceService;

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookEntity getBookById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    @Transactional
    public BookEntity saveBook(BookEntity bookEntity, Long userId, Long priceId) {
        if (userId != null) {
            UserEntity user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
            bookEntity.setUser(user);
        }

        if (priceId != null) {
            PriceEntity price = priceRepository.findById(priceId)
                    .orElseThrow(() -> new ResourceNotFoundException("Price not found with id: " + priceId));
            bookEntity.setPrice(price);
        }

        return bookRepository.save(bookEntity);
    }

    public void deleteBook(long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    public BookEntity updateBook(long bookId, BookUpdateRequest request) {
        BookEntity existingBook = getBookById(bookId);

        existingBook.setTitle(request.getBookName());
        existingBook.setAuthor(request.getAuthor());
        UserEntity user = userService.getUserById(request.getUserId());
        existingBook.setUser(user);
        PriceEntity price = priceService.getPriceById(request.getPriceId().longValue());
        existingBook.setPrice(price);


        return bookRepository.save(existingBook);
    }
}
