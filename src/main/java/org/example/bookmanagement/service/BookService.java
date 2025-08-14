package org.example.bookmanagement.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.bookmanagement.entity.BookEntity;
import org.example.bookmanagement.entity.UserEntity;
import org.example.bookmanagement.entity.PriceEntity;
import org.example.bookmanagement.model.Book;
import org.example.bookmanagement.model.BookUpdateRequest;
import org.example.bookmanagement.repository.BookRepository;
import org.example.bookmanagement.repository.UserRepository;
import org.example.bookmanagement.repository.PriceRepository;
import org.example.bookmanagement.service.UserService;
import org.example.bookmanagement.service.PriceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private  final PriceRepository priceRepository;
    private final UserService userService;
    private final PriceService priceService;

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookEntity getBookById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @Transactional
    public BookEntity saveBook(BookEntity bookEntity, Long userId, Long priceId) {
        if (userId != null) {
            UserEntity user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            bookEntity.setUser(user);
        }

        if (priceId != null) {
            PriceEntity price = priceRepository.findById(priceId)
                    .orElseThrow(() -> new RuntimeException("Price not found"));
            bookEntity.setPrice(price);
        }

        return bookRepository.save(bookEntity);
    }

    public boolean deleteBook(long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public BookEntity updateBook(BookEntity existingBook, BookUpdateRequest request) {
        if (request.getBookName() != null) {
            existingBook.setTitle(request.getBookName());
        }
        if (request.getAuthor() != null) {
            existingBook.setAuthor(request.getAuthor());
        }
        if (request.getUserId() != null) {
            UserEntity user = userService.getUserById(request.getUserId());
            if (user != null) {
                existingBook.setUser(user);
            }
        }
        if (request.getPriceId() != null) {
            PriceEntity price = priceService.getPriceById(request.getPriceId().longValue());
            if (price != null) {
                existingBook.setPrice(price);
            }
        }
        return bookRepository.save(existingBook);
    }



}
