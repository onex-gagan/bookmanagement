package org.example.bookmanagement.mapper;

import org.example.bookmanagement.entity.BookEntity;
import org.example.bookmanagement.model.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {

    public static Book toApiBook(BookEntity entity) {
        Book apiBook = new Book();

        apiBook.setBookId(entity.getId() != null ? entity.getId().intValue() : null);
        apiBook.setBookName(entity.getTitle());
        apiBook.setAuthor(entity.getAuthor());

        if (entity.getUser() != null && entity.getUser().getId() != null) {
            apiBook.setUserId(entity.getUser().getId().intValue());
        }

        if (entity.getPrice() != null && entity.getPrice().getId() != null) {
            apiBook.setPriceId(entity.getPrice().getId().intValue());
        }

        return apiBook;
    }

    public static List<Book> toApiBookList(List<BookEntity> entities) {
        return entities.stream()
                .map(BookMapper::toApiBook)
                .collect(Collectors.toList());
    }
}